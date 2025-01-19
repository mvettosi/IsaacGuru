package com.isaacguru.domain.inventory.model

abstract class Collectible : InventoryItem() {
  open val quote: String? = null
  abstract val unlockMethod: String?
}
