package com.isaacguru.domain.inventory.model

abstract class InventoryItem {
  abstract val id: String
  abstract val rawId: String
  abstract val orderId: Int
  abstract val name: String
  abstract val description: String
  abstract val image: String
  abstract val keywords: List<String>
}
