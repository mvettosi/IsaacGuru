package com.isaacguru.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.isaacguru.presentation.features.inventory.InventoryScreenRoot
import com.isaacguru.presentation.features.inventory.detail.InventoryDetailsScreenRoot
import com.isaacguru.presentation.features.others.OthersScreen
import com.isaacguru.presentation.features.settings.SettingsScreen

@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier = Modifier) {
  NavHost(navController = navController, startDestination = Screen.Inventory, modifier = modifier) {
    composable<Screen.Inventory> {
      InventoryScreenRoot(
          onNavigateToDetail = { id -> navController.navigate(Screen.InventoryDetail(id)) })
    }
    composable<Screen.InventoryDetail> {
      InventoryDetailsScreenRoot(onBackClick = navController::popBackStack)
    }
    composable<Screen.More> { OthersScreen() }
    composable<Screen.Settings> { SettingsScreen() }
  }
}
