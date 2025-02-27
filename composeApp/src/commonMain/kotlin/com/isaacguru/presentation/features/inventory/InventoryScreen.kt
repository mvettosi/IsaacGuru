package com.isaacguru.presentation.features.inventory

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.rememberAsyncImagePainter
import com.isaacguru.presentation.features.inventory.model.FilterOption
import com.isaacguru.presentation.features.inventory.model.FilterSection
import com.isaacguru.presentation.features.inventory.model.ViewInventoryItem
import com.isaacguru.presentation.features.inventory.model.ViewInventorySection
import com.isaacguru.presentation.shared.AppRed
import com.isaacguru.presentation.shared.ClearSearchRed
import com.isaacguru.presentation.shared.TopBarBackgroundColor
import com.isaacguru.presentation.shared.TopBarFocusedBackgroundColor
import com.isaacguru.presentation.shared.TopBarSearchBackgroundColor
import com.isaacguru.presentation.shared.components.AppSectionHeader
import com.isaacguru.presentation.shared.inlineDiscordMap
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryScreenRoot(
    viewModel: InventoryViewModel = koinViewModel(),
    onNavigateToDetail: (id: String) -> Unit
) {
  val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
  val coroutineScope = rememberCoroutineScope()
  val viewState by viewModel.viewState.collectAsStateWithLifecycle()

  InventoryScreen(
      sheetState = sheetState,
      viewState = viewState,
      intent = {
        when (it) {
          InventoryIntent.OnDismissFilters ->
              coroutineScope.launch { sheetState.hide() }.invokeOnCompletion {
                viewModel.intent(InventoryIntent.OnDismissFilters)
              }
          is InventoryIntent.OnItemClick -> onNavigateToDetail(it.id)
          else -> viewModel.intent(it)
        }
      })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryScreen(
    sheetState: SheetState,
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
                  value = viewState.filteringOptions.query,
                  onValueChange = { intent(InventoryIntent.OnQueryUpdated(it)) },
                  modifier = Modifier.fillMaxWidth(),
                  placeholder = { Text(text = "Search items") },
                  singleLine = true,
                  trailingIcon = {
                    if (viewState.filteringOptions.query.isNotEmpty()) {
                      FilledIconButton(
                          onClick = { intent(InventoryIntent.OnQueryUpdated("")) },
                          colors =
                              IconButtonDefaults.iconButtonColors(
                                  containerColor = ClearSearchRed, contentColor = Color.Black),
                          shape = MaterialTheme.shapes.small,
                          modifier = Modifier.requiredSize(30.dp)) {
                        Icon(Icons.Filled.Clear, contentDescription = "Clear Text")
                      }
                    }
                  },
                  colors =
                      TextFieldDefaults.colors(
                          unfocusedContainerColor = TopBarSearchBackgroundColor,
                          unfocusedIndicatorColor = TopBarSearchBackgroundColor,
                          focusedContainerColor = TopBarFocusedBackgroundColor,
                          focusedIndicatorColor = TopBarFocusedBackgroundColor))
            }
          },
          actions = {
            IconButton(onClick = { intent(InventoryIntent.OnOpenFilters) }) {
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
      if (viewState.displayFilterDialog) {
        FilterOverlay(sheetState = sheetState, viewState = viewState, intent = intent)
      }
    }
  }
}

@Composable
fun InventorySectionHeader(section: ViewInventorySection, intent: (InventoryIntent) -> Unit) {
  AppSectionHeader(
      title = section.fullTitle,
      isExpanded = !section.collapsed,
      modifier = Modifier.clickable { intent(InventoryIntent.OnSectionClick(section.title)) })
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
        painter = rememberAsyncImagePainter(item.thumbnail, filterQuality = FilterQuality.None),
        contentDescription = item.name,
        modifier = Modifier.fillMaxSize())
  }
}

fun LazyGridScope.header(content: @Composable LazyGridItemScope.() -> Unit) {
  item(span = { GridItemSpan(this.maxLineSpan) }, content = content)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterOverlay(
    sheetState: SheetState,
    viewState: InventoryViewState,
    intent: (InventoryIntent) -> Unit,
) {
  ModalBottomSheet(
      onDismissRequest = { intent(InventoryIntent.OnDismissFilters) },
      sheetState = sheetState,
      //      modifier = Modifier.safeDrawingPadding(),
      containerColor = BottomSheetDefaults.ContainerColor.copy(0.8f)) {
    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)) {
      Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = "Search Filters", style = MaterialTheme.typography.headlineMedium)
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = "Close",
            modifier = Modifier.size(30.dp).clickable { intent(InventoryIntent.OnDismissFilters) })
      }

      Button(
          onClick = { intent(InventoryIntent.OnFiltersCleared) },
          colors =
              ButtonDefaults.buttonColors(
                  contentColor = MaterialTheme.colorScheme.onSurface, containerColor = AppRed),
          shape = MaterialTheme.shapes.small,
      ) { Text("Clear all", style = MaterialTheme.typography.titleMedium) }

      FilterGroup(
          title = "Item Pools",
          options = viewState.filteringOptions.filterSections[FilterSection.ITEM_POOLS]
                  ?: emptyList(),
          onOptionSelected = {
            intent(
                InventoryIntent.OnFilterSelected(
                    filterSection = FilterSection.ITEM_POOLS, id = it.id))
          })
    }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilterGroup(
    title: String,
    options: List<FilterOption>,
    onOptionSelected: (FilterOption) -> Unit
) {
  Column {
    Text(title, style = MaterialTheme.typography.headlineSmall)
    FlowRow(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
      options.forEach { filterOption ->
        OutlinedButton(
            colors =
                ButtonDefaults.buttonColors(
                    containerColor =
                        if (filterOption.selected) filterOption.color else Color.Transparent,
                    contentColor =
                        if (filterOption.selected) filterOption.selectedTextColor
                        else filterOption.color),
            border = BorderStroke(width = 1.dp, color = filterOption.color),
            shape = MaterialTheme.shapes.small,
            contentPadding = PaddingValues(4.dp),
            onClick = { onOptionSelected(filterOption) }) {
          Text(text = filterOption.label, inlineContent = inlineDiscordMap)
        }
      }
    }
  }
}
