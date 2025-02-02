package com.isaacguru.domain.inventory.model

data class ItemFilters(
    val query: String = "",
    val itemPools: List<String> = emptyList(),
    val itemType: ItemType? = null
) {
  val noFiltering = itemPools.isEmpty() && itemType == null
}

enum class ItemType {
  ACTIVE,
  PASSIVE,
}
