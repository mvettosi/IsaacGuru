package com.isaacguru.presentation.features.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.isaacguru.domain.inventory.model.ItemFilters
import com.isaacguru.domain.inventory.usecase.GetInventoryUseCase
import com.isaacguru.domain.inventory.usecase.GetItemPoolsUseCase
import com.isaacguru.presentation.features.inventory.mappers.toFilterOption
import com.isaacguru.presentation.features.inventory.mappers.toItemFilters
import com.isaacguru.presentation.features.inventory.mappers.toViewSection
import com.isaacguru.presentation.features.inventory.model.FilterSection
import com.isaacguru.presentation.features.inventory.model.FilterSections
import com.isaacguru.presentation.features.inventory.model.ViewInventorySection
import com.isaacguru.presentation.features.inventory.model.defaultFilterSections
import com.isaacguru.presentation.shared.stateWith
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InventoryViewModel(
    private val getInventoryUseCase: GetInventoryUseCase,
    private val getItemPoolsUseCase: GetItemPoolsUseCase
) : ViewModel() {
  private var filterJob: Job? = null

  // View State
  private val _viewState = MutableStateFlow(InventoryViewState())
  val viewState =
      _viewState.stateWith(viewModelScope) {
        onStart {
          observeItemLoading()
          fetchItemPools()
        }
      }

  // View Actions
  private val _action = Channel<InventoryAction>()
  val action = _action.receiveAsFlow()

  // View Intents
  fun intent(intent: InventoryIntent) {
    viewModelScope.launch {
      when (intent) {
        is InventoryIntent.OnSectionClick -> toggleSection(intent.title)
        is InventoryIntent.OnQueryUpdated -> updateQuery(intent.query)
        InventoryIntent.OnOpenFilters -> _viewState.update { it.copy(displayFilterDialog = true) }
        InventoryIntent.OnDismissFilters ->
            _viewState.update { it.copy(displayFilterDialog = false) }
        is InventoryIntent.OnFiltersCleared -> clearFilters()
        is InventoryIntent.OnItemClick -> {
          /*Intercepted in UI*/
        }
        is InventoryIntent.OnFilterSelected -> updateFilterSelection(intent)
      }
    }
  }

  private fun clearFilters() {
    _viewState.update {
      it.copy(
          filterSections =
              it.filterSections
                  .map { entry ->
                    entry.key to entry.value.map { option -> option.copy(selected = false) }
                  }
                  .toMap())
    }
  }

  private fun updateFilterSelection(intent: InventoryIntent.OnFilterSelected) {
    _viewState.update {
      val mutableFilters = it.filterSections.toMutableMap()
      val options = mutableFilters[intent.filterSection] ?: emptyList()
      val updatedOptions =
          options.map { option ->
            if (option.id == intent.id) option.copy(selected = !option.selected) else option
          }
      mutableFilters[intent.filterSection] = updatedOptions
      it.copy(filterSections = mutableFilters.toMap())
    }
  }

  private fun updateQuery(newQuery: String) {
    _viewState.update { it.copy(query = newQuery) }
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

  private fun observeItemLoading() {
    viewState
        .map { it.filterSections }
        .distinctUntilChanged()
        .onEach { filterSections ->
          filterJob?.cancel()
          filterJob = filterItems(itemFilters = filterSections.toItemFilters(viewState.value.query))
        }
        .launchIn(viewModelScope)
  }

  private suspend fun fetchItemPools() {
    withContext(Dispatchers.IO) {
      getItemPoolsUseCase()
          .onSuccess { itemPools ->
            _viewState.update { state ->
              val mutableFilters = state.filterSections.toMutableMap()
              mutableFilters[FilterSection.ITEM_POOLS] = itemPools.map { it.toFilterOption() }
              state.copy(filterSections = mutableFilters.toMap())
            }
          }
          .onFailure { Logger.e { "Error fetching item pools: $it" } }
    }
  }

  private fun filterItems(itemFilters: ItemFilters): Job {
    _viewState.update { it.copy(isLoading = true) }
    return getInventoryUseCase(itemFilters)
        .onEach { items ->
          _viewState.update {
            it.copy(sections = items.toViewSection(!it.displayedIntoAnimation), isLoading = false)
          }
          if (!viewState.value.displayedIntoAnimation) triggerIntroAnimation()
        }
        .catch {
          Logger.e(it) { "Error getting items: $it" }
          _viewState.update { state -> state.copy(sections = emptyList(), isLoading = false) }
        }
        .launchIn(viewModelScope)
  }

  private fun triggerIntroAnimation() {
    viewModelScope.launch {
      delay(500)
      _viewState.update {
        it.copy(
            displayedIntoAnimation = true,
            sections =
                it.sections.mapIndexed { index, section ->
                  if (index == 0) section.copy(displayedItems = section.items) else section
                })
      }
    }
  }
}

data class InventoryViewState(
    val displayedIntoAnimation: Boolean = false,
    val sections: List<ViewInventorySection> = emptyList(),
    val query: String = "",
    val isLoading: Boolean = true,
    val displayFilterDialog: Boolean = false,
    val filterSections: FilterSections = defaultFilterSections,
)

sealed interface InventoryIntent {
  data class OnSectionClick(val title: String) : InventoryIntent
  data class OnItemClick(val id: String) : InventoryIntent
  data class OnQueryUpdated(val query: String) : InventoryIntent
  data object OnOpenFilters : InventoryIntent
  data object OnDismissFilters : InventoryIntent
  data object OnFiltersCleared : InventoryIntent
  data class OnFilterSelected(val filterSection: FilterSection, val id: String) : InventoryIntent
}

sealed interface InventoryAction
