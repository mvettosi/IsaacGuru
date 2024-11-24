package com.isaacguru.presentation.features.inventory.items

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.isaacguru.presentation.features.inventory.components.InventoryScreen
import com.isaacguru.presentation.features.inventory.components.model.InventoryItem
import isaacguru.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ItemsScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    displayItemDetails: (InventoryItem) -> Unit
) {
  InventoryScreen(
      title = "Items",
      onBackClick = onBackClick,
      defaultToList = false,
      inventoryItems =
          buildList {
            repeat(100) { index ->
              add(InventoryItem(name = "Item $index", thumbnail = Res.getUri("files/Isaac.webp")))
            }
          },
      onInventoryItemClick = displayItemDetails)
}
