package com.isaacguru.presentation.features.inventory.items.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.isaacguru.presentation.navigation.Screen
import com.isaacguru.presentation.util.stateWith
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

class ItemDetailsViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
  // Navigation Params
  private val params = savedStateHandle.toRoute<Screen.Inventory.Detail.Item>()

  // View State
  private val _viewState = MutableStateFlow(ItemDetailsViewState())
  val viewState =
      _viewState.stateWith(viewModelScope) {
        onStart { _viewState.update { it.copy(test = params.id) } }
      }

  // View Actions
  private val _action = Channel<ItemDetailsAction>()
  val action = _action.receiveAsFlow()

  // View Intents
  fun onIntent(action: ItemDetailsIntent) {
    when (action) {
      ItemDetailsIntent.IntentA -> intentA()
    }
  }

  private fun intentA() {}
}

data class ItemDetailsViewState(val test: String = "test")

sealed interface ItemDetailsIntent {
  data object IntentA : ItemDetailsIntent
}

sealed interface ItemDetailsAction {
  data object NavigateBack : ItemDetailsAction
}
