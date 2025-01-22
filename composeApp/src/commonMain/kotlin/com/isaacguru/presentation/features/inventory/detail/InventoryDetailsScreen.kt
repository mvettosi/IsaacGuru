package com.isaacguru.presentation.features.inventory.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.isaacguru.domain.inventory.model.Item
import com.isaacguru.presentation.features.inventory.components.DetailsTopBar
import com.isaacguru.presentation.shared.Divider
import com.isaacguru.presentation.shared.ItemDetailsBackgroundColor
import com.isaacguru.presentation.shared.ObserveEvents
import com.isaacguru.presentation.shared.QuoteColor
import com.isaacguru.presentation.shared.components.BrandText
import com.isaacguru.presentation.shared.components.GameAspectImage
import com.isaacguru.presentation.shared.components.LoadingContent
import com.isaacguru.presentation.shared.components.ResImage
import com.isaacguru.presentation.shared.inlineDiscordMap
import isaacguru.composeapp.generated.resources.Res
import isaacguru.composeapp.generated.resources.star_empty
import isaacguru.composeapp.generated.resources.star_full
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun InventoryDetailsScreenRoot(
    viewModel: InventoryDetailsViewModel = koinViewModel(),
    onBackClick: () -> Unit
) {
  val viewState by viewModel.viewState.collectAsStateWithLifecycle()

  ObserveEvents(viewModel.action) {
    when (it) {
      ItemDetailsAction.NavigateBack -> onBackClick()
    }
  }

  InventoryDetailsScreen(viewState = viewState, intent = viewModel::onIntent)
}

@Composable
fun InventoryDetailsScreen(
    viewState: ItemDetailsViewState,
    intent: (ItemDetailsIntent) -> Unit,
) {
  Surface(color = ItemDetailsBackgroundColor, contentColor = MaterialTheme.colorScheme.onSurface) {
    Column(
        modifier =
            Modifier.fillMaxSize()
                .padding(horizontal = 10.dp)
                .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally) {
      if (viewState.error != null) {
        Text(text = viewState.error.message ?: "Unknown error")
      }
      if (viewState.item == null) {
        LoadingContent()
      } else {
        DetailsTopBar(
            inventoryItem = viewState.item.raw,
            onBackClick = { intent(ItemDetailsIntent.NavigateBack) })
        if (viewState.item.raw is Item) {
          QualityRow(viewState.item.raw.quality)
        }
        BrandText(text = viewState.item.raw.name, style = MaterialTheme.typography.displayMedium)
        if (viewState.item.raw is Item) {
          viewState.item.raw.quote?.let {
            Text(
                text = "\"$it\"",
                color = QuoteColor,
                style = MaterialTheme.typography.headlineSmall)
          }
        }
        GameAspectImage(
            modifier = Modifier.requiredSize(150.dp), inventoryItem = viewState.item.raw)
        viewState.item.subtitle?.let {
          Text(
              text = it,
              style = MaterialTheme.typography.titleLarge.copy(fontStyle = Italic),
              color = Color.Gray)
        }
        DetailsDivider()
        Text(
            text = viewState.item.richDescription,
            inlineContent = inlineDiscordMap,
            modifier = Modifier.fillMaxWidth())
        DetailsDivider()
        viewState.item.richPools?.let {
          Text(
              text = viewState.item.richPools,
              inlineContent = inlineDiscordMap,
              modifier = Modifier.fillMaxWidth())
        }
      }
    }
  }
}

@Composable
fun QualityRow(quality: Int) {
  Row {
    repeat(4) { index ->
      ResImage(
          modifier = Modifier.requiredHeight(50.dp).align(Alignment.CenterVertically),
          resource = if (index < quality) Res.drawable.star_full else Res.drawable.star_empty,
          contentDescription = "Quality")
    }
  }
}

@Composable
fun DetailsDivider() {
  HorizontalDivider(
      thickness = 1.dp, color = Divider, modifier = Modifier.padding(vertical = 20.dp))
}
