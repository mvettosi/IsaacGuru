package com.isaacguru.presentation.features.inventory.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.isaacguru.presentation.features.inventory.components.InventoryScreen
import com.isaacguru.presentation.features.inventory.components.model.InventoryItem
import com.isaacguru.presentation.navigation.Screen
import isaacguru.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun InventoryHomeScreen(modifier: Modifier = Modifier, navigateToScreen: (Screen) -> Unit) {
  InventoryScreen(
      title = "Inventory",
      defaultToList = true,
      displayViewToggle = false,
      inventoryItems =
          listOf(
              InventoryItem(
                  name = "Items",
                  thumbnail = Res.getUri("files/collectibles_118_brimstone.png"),
                  destination = Screen.Inventory.Items),
              InventoryItem(
                  name = "Trinkets",
                  thumbnail = Res.getUri("files/trinket_001_swallowedpenny.png"),
                  destination = Screen.Inventory.Trinkets),
              InventoryItem(
                  name = "Pickups",
                  thumbnail = Res.getUri("files/Heart.webp"),
                  destination = Screen.Inventory.Pickups),
              InventoryItem(
                  name = "Consumables",
                  thumbnail = Res.getUri("files/Item_Card.webp"),
                  destination = Screen.Inventory.Consumables),
              InventoryItem(
                  name = "Machines",
                  thumbnail = Res.getUri("files/Slot_Machine.webp"),
                  destination = Screen.Inventory.Machines),
              InventoryItem(
                  name = "Characters",
                  thumbnail = Res.getUri("files/Isaac.webp"),
                  destination = Screen.Inventory.Characters),
              InventoryItem(
                  name = "Transformations",
                  thumbnail = Res.getUri("files/Guppy.webp"),
                  destination = Screen.Inventory.Transformations),
              InventoryItem(
                  name = "Curses",
                  thumbnail = Res.getUri("files/Curse of Darkness.webp"),
                  destination = Screen.Inventory.Curses),
          ),
      onInventoryItemClick = {
        it.destination?.let { destination -> navigateToScreen(destination) }
      })
}
