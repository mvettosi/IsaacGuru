package com.isaacguru.presentation.features.inventory

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.rememberAsyncImagePainter
import com.isaacguru.presentation.features.inventory.model.ViewInventoryItem
import com.isaacguru.presentation.features.inventory.model.ViewInventorySection
import com.isaacguru.presentation.shared.ObserveEvents
import com.isaacguru.presentation.shared.TopBarBackgroundColor
import com.isaacguru.presentation.shared.TopBarFocusedBackgroundColor
import com.isaacguru.presentation.shared.TopBarSearchBackgroundColor
import com.isaacguru.presentation.shared.components.BrandText
import com.isaacguru.presentation.shared.components.ResImage
import isaacguru.composeapp.generated.resources.Res
import isaacguru.composeapp.generated.resources.effect_streak
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun InventoryScreenRoot(
    viewModel: InventoryViewModel = koinViewModel(),
    onNavigateToDetail: (id: String) -> Unit
) {
  val viewState by viewModel.viewState.collectAsStateWithLifecycle()

  ObserveEvents(viewModel.action) {
    when (it) {
      is InventoryAction.NavigateToDetail -> onNavigateToDetail(it.id)
    }
  }

  InventoryScreen(viewState = viewState, intent = viewModel::intent)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryScreen(
    viewState: InventoryViewState,
    intent: (InventoryIntent) -> Unit,
) {
  Surface(contentColor = MaterialTheme.colorScheme.onSurface, color = Color.Transparent) {
    Column {
      TopAppBar(
          windowInsets = WindowInsets(0.dp),
          colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = TopBarBackgroundColor),
          modifier = Modifier.wrapContentHeight(),
          title = {
            Box(modifier = Modifier.padding(5.dp)) {
              OutlinedTextField(
                  value = viewState.itemFilters.query,
                  onValueChange = { intent(InventoryIntent.OnQueryUpdated(it)) },
                  modifier = Modifier.fillMaxWidth(),
                  placeholder = { Text(text = "Search items") },
                  colors =
                      TextFieldDefaults.colors(
                          unfocusedContainerColor = TopBarSearchBackgroundColor,
                          unfocusedIndicatorColor = TopBarSearchBackgroundColor,
                          focusedContainerColor = TopBarFocusedBackgroundColor,
                          focusedIndicatorColor = TopBarFocusedBackgroundColor))
            }
          },
          actions = {
            IconButton(onClick = { /* doSomething() */}) {
              Icon(imageVector = Icons.Filled.Edit, contentDescription = "Filter")
            }
          })
      LazyVerticalGrid(
          modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp),
          columns = GridCells.Fixed(5),
      ) {
        viewState.sections.forEach { section ->
          header { InventorySectionHeader(section = section, intent = intent) }
          items(items = section.displayedItems ?: emptyList(), key = { it.id }) { item ->
            InventorySectionItem(item = item, intent = intent, modifier = Modifier.animateItem())
          }
        }
      }
    }
  }
}

@Composable
fun InventorySectionHeader(section: ViewInventorySection, intent: (InventoryIntent) -> Unit) {
  Box(
      contentAlignment = Alignment.Center,
      modifier = Modifier.clickable { intent(InventoryIntent.OnSectionClick(section.title)) }) {
    ResImage(
        modifier = Modifier.fillMaxWidth().requiredHeight(70.dp),
        contentScale = ContentScale.FillBounds,
        resource = Res.drawable.effect_streak,
        contentDescription = "Effect Streak")
    BrandText(
        text = section.fullTitle,
        style = MaterialTheme.typography.headlineMedium,
        modifier = Modifier.offset(y = (-8).dp))
  }
}

@Composable
fun InventorySectionItem(
    item: ViewInventoryItem,
    modifier: Modifier = Modifier,
    intent: (InventoryIntent) -> Unit
) {
  Box(
      modifier =
          modifier
              .clickable { intent(InventoryIntent.OnItemClick(item.id)) }
              .aspectRatio(1f)
              .padding(8.dp),
      contentAlignment = Alignment.Center,
  ) {
    Image(
        painter = rememberAsyncImagePainter(item.thumbnail),
        contentDescription = item.name,
        modifier = Modifier.fillMaxSize())
  }
}

fun LazyGridScope.header(content: @Composable LazyGridItemScope.() -> Unit) {
  item(span = { GridItemSpan(this.maxLineSpan) }, content = content)
}
