package com.isaacguru.presentation.features.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.isaacguru.domain.inventory.model.ItemFilters
import com.isaacguru.domain.inventory.usecase.GetInventoryUseCase
import com.isaacguru.presentation.features.inventory.mappers.toViewSection
import com.isaacguru.presentation.features.inventory.model.ViewInventorySection
import com.isaacguru.presentation.shared.stateWith
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class InventoryViewModel(private val getInventoryUseCase: GetInventoryUseCase) : ViewModel() {
  private var filterJob: Job? = null

  // View State
  private val _viewState = MutableStateFlow(InventoryViewState())
  val viewState = _viewState.stateWith(viewModelScope) { onStart { observeItemLoading() } }

  // View Actions
  private val _action = Channel<InventoryAction>()
  val action = _action.receiveAsFlow()

  // View Intents
  fun intent(intent: InventoryIntent) {
    viewModelScope.launch {
      when (intent) {
        is InventoryIntent.OnLoadItems ->
            _viewState.update { it.copy(itemFilters = intent.itemFilters) }
        is InventoryIntent.OnSectionClick -> toggleSection(intent.title)
        is InventoryIntent.OnItemClick -> _action.send(InventoryAction.NavigateToDetail(intent.id))
        is InventoryIntent.OnQueryUpdated -> updateQuery(intent.query)
      }
    }
  }

  private fun updateQuery(newQuery: String) {
    _viewState.update { it.copy(itemFilters = it.itemFilters.copy(query = newQuery)) }
  }

  private fun toggleSection(title: String) {
    _viewState.update {
      it.copy(
          sections =
              it.sections.map { section ->
                if (section.title == title)
                    section.copy(displayedItems = if (section.collapsed) section.items else null)
                else section
              })
    }
  }

  @OptIn(FlowPreview::class)
  private fun observeItemLoading() {
    viewState
        .map { it.itemFilters }
        .debounce(1000L)
        .distinctUntilChanged()
        .onEach { itemFilters ->
          filterJob?.cancel()
          filterJob = filterItems(itemFilters = itemFilters)
        }
        .launchIn(viewModelScope)
  }

  private fun filterItems(itemFilters: ItemFilters): Job =
      viewModelScope.launch(Dispatchers.IO) {
        _viewState.update { it.copy(isLoading = true) }
        getInventoryUseCase(itemFilters)
            .onSuccess { items ->
              _viewState.update {
                it.copy(sections = items.toViewSection(itemFilters.noFiltering), isLoading = false)
              }
              if (itemFilters.noFiltering) onFullSearchAnimation()
            }
            .onFailure {
              Logger.e { "Error getting items: $it" }
              _viewState.update { it.copy(sections = emptyList(), isLoading = false) }
            }
      }

  private fun onFullSearchAnimation() {
    viewModelScope.launch {
      delay(500)
      _viewState.update {
        it.copy(
            sections =
                it.sections.mapIndexed { index, section ->
                  if (index == 0) section.copy(displayedItems = section.items) else section
                })
      }
    }
  }
}

data class InventoryViewState(
    val sections: List<ViewInventorySection> = emptyList(),
    val itemFilters: ItemFilters = ItemFilters(),
    val isLoading: Boolean = true
)

sealed interface InventoryIntent {
  data class OnLoadItems(val itemFilters: ItemFilters = ItemFilters()) : InventoryIntent
  data class OnSectionClick(val title: String) : InventoryIntent
  data class OnItemClick(val id: String) : InventoryIntent
  data class OnQueryUpdated(val query: String) : InventoryIntent
}

sealed interface InventoryAction {
  data class NavigateToDetail(val id: String) : InventoryAction
}
