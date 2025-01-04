package com.isaacguru.presentation.features.inventory.items

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.isaacguru.domain.collectable.item.model.ItemFilters
import com.isaacguru.domain.collectable.item.usecase.GetItemsUseCase
import com.isaacguru.presentation.features.inventory.items.mapper.toViewItem
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ItemsViewModel(private val getItemsUseCase: GetItemsUseCase) : ViewModel() {
  private var filterJob: Job? = null

  private val _viewState = MutableStateFlow(ItemsViewState())
  val state =
      _viewState
          .onStart { observeItemLoading() }
          .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), _viewState.value)

  fun onAction(action: ItemsAction) {
    when (action) {
      is ItemsAction.OnLoadItems -> _viewState.update { it.copy(itemFilters = action.itemFilters) }
    }
  }

  private fun observeItemLoading() {
    state
        .map { it.itemFilters }
        .distinctUntilChanged()
        .onEach { itemFilters ->
          filterJob?.cancel()
          filterJob = filterItems(itemFilters = itemFilters)
        }
        .launchIn(viewModelScope)
  }

  private fun filterItems(itemFilters: ItemFilters): Job =
      viewModelScope.launch {
        _viewState.update { it.copy(isLoading = true) }
        getItemsUseCase(itemFilters)
            .onSuccess { items ->
              _viewState.update {
                it.copy(items = items.map { item -> item.toViewItem() }, isLoading = false)
              }
            }
            .onFailure {
              Logger.e { "Error getting items: $it" }
              _viewState.update { it.copy(items = emptyList(), isLoading = false) }
            }
      }
}
