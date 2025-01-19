package com.isaacguru.presentation.features.inventory.characters

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.isaacguru.presentation.features.inventory.components.InventoryScreen
import com.isaacguru.presentation.features.inventory.components.model.ViewInventoryItem
import isaacguru.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CharactersScreen(modifier: Modifier = Modifier, onBackClick: () -> Unit) {
  InventoryScreen(
      title = "Characters",
      onBackClick = onBackClick,
      defaultToList = false,
      viewInventoryItems =
          buildList {
            repeat(100) { index ->
              add(
                  ViewInventoryItem(
                      name = "Item $index", thumbnail = Res.getUri("files/Isaac.webp")))
            }
          },
      onInventoryItemClick = {})
}
