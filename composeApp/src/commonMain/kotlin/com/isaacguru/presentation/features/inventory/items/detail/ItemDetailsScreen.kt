package com.isaacguru.presentation.features.inventory.items.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.rememberAsyncImagePainter
import com.isaacguru.presentation.features.inventory.components.DetailsTopBar
import com.isaacguru.presentation.shared.ItemDetailsBackgroundColor
import com.isaacguru.presentation.shared.ObserveEvents
import com.isaacguru.presentation.shared.QuoteColor
import com.isaacguru.presentation.shared.components.BrandText
import com.isaacguru.presentation.shared.components.LoadingContent
import isaacguru.composeapp.generated.resources.Res
import isaacguru.composeapp.generated.resources.star_empty
import isaacguru.composeapp.generated.resources.star_full
import org.jetbrains.compose.resources.imageResource
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
  Surface(color = ItemDetailsBackgroundColor, contentColor = MaterialTheme.colorScheme.onSurface) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
      if (viewState.error != null) {
        Text(text = viewState.error.message ?: "Unknown error")
      }
      if (viewState.item == null) {
        LoadingContent()
      } else {
        DetailsTopBar(
            gameAspect = viewState.item, onBackClick = { intent(ItemDetailsIntent.NavigateBack) })
        QualityRow(viewState.item.quality)
        BrandText(text = viewState.item.name, style = MaterialTheme.typography.displayMedium)
        viewState.item.quote?.let {
          Text(text = "\"$it\"", color = QuoteColor, style = MaterialTheme.typography.headlineSmall)
        }
        Image(
            painter =
                rememberAsyncImagePainter(viewState.item.image, filterQuality = FilterQuality.None),
            contentDescription = viewState.item.name,
            modifier = Modifier.requiredSize(150.dp))
      }
    }
  }
}

@Composable
fun QualityRow(quality: Int) {
  Row {
    repeat(4) { index ->
      Image(
          bitmap =
              imageResource(
                  if (index < quality) Res.drawable.star_full else Res.drawable.star_empty),
          contentDescription = "Quality",
          modifier = Modifier.requiredHeight(50.dp).align(Alignment.CenterVertically),
          contentScale = ContentScale.FillHeight,
          filterQuality = FilterQuality.None)
    }
  }
}
