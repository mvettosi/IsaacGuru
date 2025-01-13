package com.isaacguru.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.isaacguru.presentation.features.inventory.characters.CharactersScreen
import com.isaacguru.presentation.features.inventory.home.InventoryHomeScreen
import com.isaacguru.presentation.features.inventory.items.ItemsScreen
import com.isaacguru.presentation.features.inventory.items.detail.ItemDetailsScreenRoot
import com.isaacguru.presentation.features.inventory.pickups.PickupsScreen
import com.isaacguru.presentation.features.inventory.transformations.TransformationsScreen
import com.isaacguru.presentation.features.inventory.trinkets.TrinketsScreen
import com.isaacguru.presentation.features.search.SearchScreen
import com.isaacguru.presentation.features.settings.SettingsScreen

@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier = Modifier) {
  NavHost(
      navController = navController,
      startDestination = Screen.Inventory.Detail.Item("5"),
      modifier = modifier) {
    // Home screen tabs
    composable<Screen.Inventory.Home> {
      InventoryHomeScreen(navigateToScreen = navController::navigate)
    }
    composable<Screen.Search> { SearchScreen() }
    composable<Screen.Settings> { SettingsScreen() }

    // Inventory grids
    composable<Screen.Inventory.Characters> {
      CharactersScreen(onBackClick = navController::popBackStack)
    }
    composable<Screen.Inventory.Items> {
      ItemsScreen(
          onBackClick = navController::popBackStack,
          displayItemDetails = { navController.navigate(Screen.Inventory.Detail.Item(it.id)) })
    }
    composable<Screen.Inventory.Trinkets> {
      TrinketsScreen(onBackClick = navController::popBackStack)
    }
    composable<Screen.Inventory.Pickups> {
      PickupsScreen(onBackClick = navController::popBackStack)
    }
    composable<Screen.Inventory.Transformations> {
      TransformationsScreen(onBackClick = navController::popBackStack)
    }

    // Inventory details
    composable<Screen.Inventory.Detail.Item> {
      ItemDetailsScreenRoot(onBackClick = navController::popBackStack)
    }
  }
}
