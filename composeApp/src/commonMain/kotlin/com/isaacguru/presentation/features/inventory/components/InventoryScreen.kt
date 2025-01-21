package com.isaacguru.presentation.features.inventory.components

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.isaacguru.presentation.features.inventory.model.ViewInventoryItem
import com.isaacguru.presentation.shared.components.LoadingContent

@Composable
fun InventoryScreen(
    title: String,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null,
    onFilterClick: (() -> Unit)? = null,
    defaultToList: Boolean = true,
    displayViewToggle: Boolean = true,
    columns: Int = 5,
    viewInventoryItems: List<ViewInventoryItem>,
    onInventoryItemClick: (ViewInventoryItem) -> Unit,
    isLoading: Boolean = false
) {
  var showList by remember { mutableStateOf(defaultToList) }
  val toggleView = { showList = !showList }
  if (isLoading) {
    LoadingContent(modifier = modifier)
  } else {
    Column(modifier = modifier) {
      InventoryTopBar(
          title = title,
          onBackClick = onBackClick,
          onFilterClick = onFilterClick,
          onListClick = if (displayViewToggle) toggleView else null,
      )
      if (showList) {
        InventoryList(
            viewInventoryItems = viewInventoryItems, onInventoryItemClick = onInventoryItemClick)
      } else {
        InventoryGrid(
            viewInventoryItems = viewInventoryItems,
            onInventoryItemClick = onInventoryItemClick,
            columns = columns)
      }
    }
  }
}

@Composable
private fun InventoryList(
    viewInventoryItems: List<ViewInventoryItem>,
    onInventoryItemClick: (ViewInventoryItem) -> Unit
) {
  LazyColumn(
      verticalArrangement = Arrangement.spacedBy(8.dp),
      contentPadding = (PaddingValues(8.dp)),
  ) {
    viewInventoryItems.forEach { inventoryItem ->
      item {
        Row(
            modifier =
                Modifier.fillMaxWidth()
                    .border(width = 1.dp, color = MaterialTheme.colorScheme.onSurface)
                    .padding(8.dp)
                    .clickable { onInventoryItemClick(inventoryItem) },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp)) {
          Image(
              painter = rememberAsyncImagePainter(inventoryItem.thumbnail),
              contentDescription = inventoryItem.name,
              modifier = Modifier.size(50.dp))
          Column(modifier = Modifier.weight(1f)) {
            Text(
                text = inventoryItem.name,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
            inventoryItem.summary?.let {
              Text(
                  text = it,
                  color = MaterialTheme.colorScheme.onSurface,
                  style = MaterialTheme.typography.bodyMedium)
            }
          }
        }
      }
    }
  }
}

@Composable
private fun InventoryGrid(
    viewInventoryItems: List<ViewInventoryItem>,
    columns: Int = 5,
    onInventoryItemClick: (ViewInventoryItem) -> Unit
) {
  LazyVerticalGrid(columns = GridCells.Fixed(columns)) {
    viewInventoryItems.forEach { inventoryItem ->
      item {
        Box(
            modifier =
                Modifier.clickable { onInventoryItemClick(inventoryItem) }
                    .aspectRatio(1f)
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
