package com.isaacguru.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
  sealed class Inventory : Screen() {
    @Serializable data object InventoryHome : Inventory()
    @Serializable data object Achievements : Inventory()
    @Serializable data object Bosses : Inventory()
    @Serializable data object Characters : Inventory()
    @Serializable data object DiceRooms : Inventory()
    @Serializable data object Enemies : Inventory()
    @Serializable data object Items : Inventory()
    @Serializable data object Pickups : Inventory()
    @Serializable data object Transformations : Inventory()
    @Serializable data object Trinkets : Inventory()
    @Serializable
    sealed class Detail : Inventory() {
      abstract val id: String
      @Serializable data class Achievement(override val id: String) : Detail()
      @Serializable data class Boss(override val id: String) : Detail()
      @Serializable data class Character(override val id: String) : Detail()
      @Serializable data class Enemy(override val id: String) : Detail()
      @Serializable data class Item(override val id: String) : Detail()
      @Serializable data class Pickup(override val id: String) : Detail()
      @Serializable data class Transformation(override val id: String) : Detail()
      @Serializable data class Trinket(override val id: String) : Detail()
    }
  }
  @Serializable data object Search : Screen()
  @Serializable data object Settings : Screen()
}
