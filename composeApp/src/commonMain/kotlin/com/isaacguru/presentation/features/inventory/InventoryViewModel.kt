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
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
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
  fun onIntent(action: InventoryIntent) {
    viewModelScope.launch {
      when (action) {
        is InventoryIntent.OnLoadItems ->
            _viewState.update { it.copy(itemFilters = action.itemFilters) }
        is InventoryIntent.OnSectionClick -> toggleSection(action.title)
        is InventoryIntent.OnItemClick -> _action.send(InventoryAction.NavigateToDetail(action.id))
      }
    }
  }

  private fun toggleSection(title: String) {
    _viewState.update {
      it.copy(
          sections =
              it.sections.map { section ->
                if (section.title == title) section.copy(collapsed = !section.collapsed)
                else section
              })
    }
  }

  private fun observeItemLoading() {
    viewState
        .map { it.itemFilters }
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
              _viewState.update { it.copy(sections = items.toViewSection(), isLoading = false) }
            }
            .onFailure {
              Logger.e { "Error getting items: $it" }
              _viewState.update { it.copy(sections = emptyList(), isLoading = false) }
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
}

sealed interface InventoryAction {
  data object NavigateBack : InventoryAction
  data class NavigateToDetail(val id: String) : InventoryAction
}
