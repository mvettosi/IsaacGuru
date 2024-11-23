package com.isaacguru.presentation.screens.inventory.bosses

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.isaacguru.presentation.screens.inventory.shared.InventoryScreen
import com.isaacguru.presentation.screens.inventory.shared.model.InventoryItem
import isaacguru.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun BossesScreen(modifier: Modifier = Modifier, onBackClick: () -> Unit) {
  InventoryScreen(
      title = "Bosses",
      onBackClick = onBackClick,
      defaultToList = false,
      inventoryItems =
          buildList {
            repeat(100) { index ->
              add(
                  InventoryItem(
                      name = "Item $index", thumbnail = Res.getUri("files/Isaac.webp"))
              )
            }
          },
      onInventoryItemClick = {})
}
