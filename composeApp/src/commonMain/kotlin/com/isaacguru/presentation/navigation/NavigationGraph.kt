package com.isaacguru.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.isaacguru.presentation.screens.inventory.achievements.AchievementsScreen
import com.isaacguru.presentation.screens.inventory.bosses.BossesScreen
import com.isaacguru.presentation.screens.inventory.characters.CharactersScreen
import com.isaacguru.presentation.screens.inventory.dice_rooms.DiceRoomsScreen
import com.isaacguru.presentation.screens.inventory.enemies.EnemiesScreen
import com.isaacguru.presentation.screens.inventory.home.InventoryHomeScreen
import com.isaacguru.presentation.screens.inventory.items.ItemDetailsScreen
import com.isaacguru.presentation.screens.inventory.items.ItemsScreen
import com.isaacguru.presentation.screens.inventory.pickups.PickupsScreen
import com.isaacguru.presentation.screens.inventory.transformations.TransformationsScreen
import com.isaacguru.presentation.screens.inventory.trinkets.TrinketsScreen
import com.isaacguru.presentation.screens.search.SearchScreen
import com.isaacguru.presentation.screens.settings.SettingsScreen

@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier = Modifier) {
  NavHost(
      navController = navController,
      startDestination = Screen.Inventory.InventoryHome,
      modifier = modifier) {
    // Home screen tabs
    composable<Screen.Inventory.InventoryHome> {
      InventoryHomeScreen(navigateToScreen = { screen -> navController.navigate(screen) })
    }
    composable<Screen.Search> { SearchScreen() }
    composable<Screen.Settings> { SettingsScreen() }

    // Inventory grids
    composable<Screen.Inventory.Achievements> {
      AchievementsScreen(onBackClick = { navController.popBackStack() })
    }
    composable<Screen.Inventory.Bosses> {
      BossesScreen(onBackClick = { navController.popBackStack() })
    }
    composable<Screen.Inventory.Characters> {
      CharactersScreen(onBackClick = { navController.popBackStack() })
    }
    composable<Screen.Inventory.DiceRooms> {
      DiceRoomsScreen(onBackClick = { navController.popBackStack() })
    }
    composable<Screen.Inventory.Enemies> {
      EnemiesScreen(onBackClick = { navController.popBackStack() })
    }
    composable<Screen.Inventory.Items> {
      ItemsScreen(
          onBackClick = { navController.popBackStack() },
          displayItemDetails = { navController.navigate(Screen.Inventory.Detail.Item(it.id)) })
    }
    composable<Screen.Inventory.Pickups> {
      PickupsScreen(onBackClick = { navController.popBackStack() })
    }
    composable<Screen.Inventory.Transformations> {
      TransformationsScreen(onBackClick = { navController.popBackStack() })
    }
    composable<Screen.Inventory.Trinkets> {
      TrinketsScreen(onBackClick = { navController.popBackStack() })
    }

    // Inventory details
    composable<Screen.Inventory.Detail.Item> {
      ItemDetailsScreen(onBackClick = { navController.popBackStack() })
    }
  }
}
