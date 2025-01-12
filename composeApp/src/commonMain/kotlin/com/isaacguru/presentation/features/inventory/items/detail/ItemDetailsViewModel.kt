package com.isaacguru.presentation.features.inventory.items.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import co.touchlab.kermit.Logger
import com.isaacguru.domain.collectable.item.model.Item
import com.isaacguru.domain.collectable.item.usecase.GetItemUseCase
import com.isaacguru.presentation.navigation.Screen
import com.isaacguru.presentation.shared.stateWith
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ItemDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val getItemUseCase: GetItemUseCase
) : ViewModel() {
  // Navigation Params
  private val params = savedStateHandle.toRoute<Screen.Inventory.Detail.Item>()

  // View State
  private val _viewState = MutableStateFlow(ItemDetailsViewState())
  val viewState =
      _viewState.stateWith(viewModelScope) {
        onStart {
          Logger.i { "Fetching data for ${params.id}" }
          fetchItemData()
        }
      }

  // View Actions
  private val _action = Channel<ItemDetailsAction>()
  val action = _action.receiveAsFlow()

  // View Intents
  fun onIntent(intent: ItemDetailsIntent) {
    viewModelScope.launch {
      when (intent) {
        ItemDetailsIntent.NavigateBack -> _action.send(ItemDetailsAction.NavigateBack)
      }
    }
  }

  private fun fetchItemData() =
      viewModelScope.launch(Dispatchers.IO) {
        getItemUseCase(params.id)
            .onSuccess { result ->
              Logger.i { "Fetched item ${result.name}" }
              _viewState.update { it.copy(isLoading = false, item = result) }
            }
            .onFailure {
              Logger.e { "Failed to fetch item ${params.id}" }
              _viewState.update { it.copy(isLoading = false, error = it.error) }
            }
      }
}

data class ItemDetailsViewState(
    val isLoading: Boolean = true,
    val error: Throwable? = null,
    val item: Item? = null
)

sealed interface ItemDetailsIntent {
  data object NavigateBack : ItemDetailsIntent
}

sealed interface ItemDetailsAction {
  data object NavigateBack : ItemDetailsAction
}
