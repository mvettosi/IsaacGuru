package com.isaacguru.presentation.features.inventory.items.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
  Text(viewState.test)
}
