package com.isaacguru.presentation.features.inventory.items.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.isaacguru.presentation.features.inventory.components.InventoryTopBar
import com.isaacguru.presentation.util.ObserveEvents
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ItemDetailsScreenRoot(
    viewModel: ItemDetailsViewModel = koinViewModel(),
    onBackClick: () -> Unit
) {
  val viewState by viewModel.viewState.collectAsStateWithLifecycle()

  ObserveEvents(viewModel.action) {
    when (it) {
      ItemDetailsAction.NavigateBack -> onBackClick()
    }
  }

  ItemDetailsScreen(viewState = viewState, intent = viewModel::onIntent)
}

@Composable
fun ItemDetailsScreen(
    viewState: ItemDetailsViewState,
    intent: (ItemDetailsIntent) -> Unit,
) {
  Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
    if (viewState.isLoading) {
      CircularProgressIndicator()
    }
    if (viewState.error != null) {
      Text(text = viewState.error.message ?: "Unknown error")
    }
    if (viewState.item != null) {
      InventoryTopBar(
          title = viewState.item.name, onBackClick = { intent(ItemDetailsIntent.NavigateBack) })
    }
  }
}
