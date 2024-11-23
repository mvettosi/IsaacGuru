package com.isaacguru.presentation.screens.inventory.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.isaacguru.presentation.screens.inventory.shared.components.InventoryTopBar
import com.isaacguru.presentation.screens.inventory.shared.model.InventoryItem
import isaacguru.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun InventoryScreen(
  title: String,
  modifier: Modifier = Modifier,
  onBackClick: (() -> Unit)? = null,
  onFilterClick: (() -> Unit)? = null,
  defaultToList: Boolean = true,
  displayViewToggle: Boolean = true,
  columns: Int = 5,
  inventoryItems: List<InventoryItem>,
  onInventoryItemClick: (InventoryItem) -> Unit,
) {
  var showList by remember { mutableStateOf(defaultToList) }
  val toggleView = { showList = !showList }
  Column(modifier = modifier) {
    InventoryTopBar(
        title = title,
        onBackClick = onBackClick,
        onFilterClick = onFilterClick,
        onListClick = if (displayViewToggle) toggleView else null,
    )
    if (showList) {
      InventoryList(inventoryItems = inventoryItems, onInventoryItemClick = onInventoryItemClick)
    } else {
      InventoryGrid(
          inventoryItems = inventoryItems,
          onInventoryItemClick = onInventoryItemClick,
          columns = columns)
    }
  }
}

@Composable
private fun InventoryList(
  inventoryItems: List<InventoryItem>,
  onInventoryItemClick: (InventoryItem) -> Unit
) {
  LazyColumn(
      verticalArrangement = Arrangement.spacedBy(8.dp),
      contentPadding = (PaddingValues(8.dp)),
  ) {
    inventoryItems.forEach { inventoryItem ->
      item {
        Row(
            modifier =
                Modifier.fillMaxWidth()
                    .border(width = 1.dp, color = Color.Black)
                    .padding(8.dp)
                    .clickable { onInventoryItemClick(inventoryItem) },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp)) {
          Image(
              painter = rememberAsyncImagePainter(inventoryItem.thumbnail),
              contentDescription = inventoryItem.name,
              modifier = Modifier.size(50.dp))
          Text(text = inventoryItem.name, modifier = Modifier.weight(1f))
        }
      }
    }
  }
}

@Composable
private fun InventoryGrid(
  inventoryItems: List<InventoryItem>,
  columns: Int = 5,
  onInventoryItemClick: (InventoryItem) -> Unit
) {
  LazyVerticalGrid(columns = GridCells.Fixed(columns)) {
    inventoryItems.forEach { inventoryItem ->
      item {
        Box(
            modifier =
                Modifier.clickable { onInventoryItemClick(inventoryItem) }
                    .aspectRatio(1f)
                    .padding(5.dp)
                    .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(5.dp))
                    .padding(8.dp),
            contentAlignment = Alignment.Center,
        ) {
          Image(
              painter = rememberAsyncImagePainter(inventoryItem.thumbnail),
              contentDescription = inventoryItem.name,
              modifier = Modifier.fillMaxSize())
        }
      }
    }
  }
}

@OptIn(ExperimentalResourceApi::class)
@Preview
@Composable
fun InventoryListPreview() {
  val inventoryItems =
      listOf(
          InventoryItem(name = "Item 1", thumbnail = Res.getUri("files/Isaac.webp")),
          InventoryItem(name = "Item 2", thumbnail = Res.getUri("files/Isaac.webp")),
          InventoryItem(name = "Item 3", thumbnail = Res.getUri("files/Isaac.webp")),
      )
  InventoryScreen(
      title = "Inventory",
      onBackClick = {},
      onFilterClick = {},
      defaultToList = true,
      inventoryItems = inventoryItems,
      onInventoryItemClick = {})
}

@OptIn(ExperimentalResourceApi::class)
@Preview
@Composable
fun InventoryGridPreview() {
  val inventoryItems =
      listOf(
          InventoryItem(name = "Item 1", thumbnail = Res.getUri("files/Isaac.webp")),
          InventoryItem(name = "Item 2", thumbnail = Res.getUri("files/Isaac.webp")),
          InventoryItem(name = "Item 3", thumbnail = Res.getUri("files/Isaac.webp")),
      )
  InventoryScreen(
      title = "Inventory",
      onBackClick = {},
      onFilterClick = {},
      defaultToList = false,
      columns = 3,
      inventoryItems = inventoryItems,
      onInventoryItemClick = {})
}
