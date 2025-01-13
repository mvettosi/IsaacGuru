package com.isaacguru.presentation.features.inventory.items.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.isaacguru.presentation.features.inventory.components.DetailsTopBar
import com.isaacguru.presentation.shared.ObserveEvents
import com.isaacguru.presentation.shared.QuoteColor
import com.isaacguru.presentation.shared.components.BrandText
import com.isaacguru.presentation.shared.components.LoadingContent
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
  Surface {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
      if (viewState.error != null) {
        Text(text = viewState.error.message ?: "Unknown error")
      }
      if (viewState.item == null) {
        LoadingContent()
      } else {
        DetailsTopBar(
            gameAspect = viewState.item, onBackClick = { intent(ItemDetailsIntent.NavigateBack) })
        BrandText(text = viewState.item.name, style = MaterialTheme.typography.displayMedium)
        viewState.item.quote?.let { Text(text = "\"$it\"", color = QuoteColor) }
      }
    }
  }
}
