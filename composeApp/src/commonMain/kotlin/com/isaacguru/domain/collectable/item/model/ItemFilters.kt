package com.isaacguru.domain.collectable.item.model

data class ItemFilters(val itemPools: List<String> = emptyList(), val itemType: ItemType? = null)

enum class ItemType {
  ACTIVE,
  PASSIVE
}
