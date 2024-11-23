package com.isaacguru.presentation.screens.inventory.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.isaacguru.presentation.navigation.Screen
import com.isaacguru.presentation.screens.inventory.shared.InventoryScreen
import com.isaacguru.presentation.screens.inventory.shared.model.InventoryItem
import isaacguru.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview

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
                  thumbnail = Res.getUri("files/Isaac.webp"),
                  destination = Screen.Inventory.Items),
              InventoryItem(
                  name = "Trinkets",
                  thumbnail = Res.getUri("files/Isaac.webp"),
                  destination = Screen.Inventory.Trinkets),
              InventoryItem(
                  name = "Pickups",
                  thumbnail = Res.getUri("files/Isaac.webp"),
                  destination = Screen.Inventory.Pickups),
              InventoryItem(
                  name = "Enemies",
                  thumbnail = Res.getUri("files/Isaac.webp"),
                  destination = Screen.Inventory.Enemies),
              InventoryItem(
                  name = "Bosses",
                  thumbnail = Res.getUri("files/Isaac.webp"),
                  destination = Screen.Inventory.Bosses),
              InventoryItem(
                  name = "Characters",
                  thumbnail = Res.getUri("files/Isaac.webp"),
                  destination = Screen.Inventory.Characters),
              InventoryItem(
                  name = "Transformations",
                  thumbnail = Res.getUri("files/Isaac.webp"),
                  destination = Screen.Inventory.Transformations),
              InventoryItem(
                  name = "Achievements",
                  thumbnail = Res.getUri("files/Isaac.webp"),
                  destination = Screen.Inventory.Achievements),
              InventoryItem(
                  name = "Dice Rooms",
                  thumbnail = Res.getUri("files/Isaac.webp"),
                  destination = Screen.Inventory.DiceRooms),
          ),
      onInventoryItemClick = {
        it.destination?.let { destination -> navigateToScreen(destination) }
      })
}

@Preview
@Composable
fun InventoryHomeScreenPreview() {
  InventoryHomeScreen(navigateToScreen = {})
}
