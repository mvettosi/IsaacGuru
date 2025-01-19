package com.isaacguru.domain.inventory.model

data class ItemFilters(val itemPools: List<String> = emptyList(), val itemType: ItemType? = null)

enum class ItemType {
  ACTIVE,
  PASSIVE,
}
