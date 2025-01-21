package com.isaacguru.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
  @Serializable data object Inventory : Screen()
  @Serializable data class InventoryDetail(val id: String) : Screen()
  @Serializable data object More : Screen()
  @Serializable data object Settings : Screen()
}
