package com.isaacguru.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
  sealed class Inventory : Screen() {
    @Serializable data object Home : Inventory()
    @Serializable data object Curses : Inventory()
    @Serializable data object Machines : Inventory()
    @Serializable data object Characters : Inventory()
    @Serializable data object Consumables : Inventory()
    @Serializable data object Items : Inventory()
    @Serializable data object Pickups : Inventory()
    @Serializable data object Transformations : Inventory()
    @Serializable data object Trinkets : Inventory()
    @Serializable
    sealed class Detail : Inventory() {
      abstract val id: String
      @Serializable data class Item(override val id: String) : Detail()
    }
  }
  @Serializable data object Search : Screen()
  @Serializable data object Settings : Screen()
}
