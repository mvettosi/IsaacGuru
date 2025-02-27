package com.isaacguru.presentation.features.inventory.model

data class ViewInventorySection(
    val title: String,
    val items: List<ViewInventoryItem>,
    val displayedItems: List<ViewInventoryItem>? = null
) {
  val collapsed: Boolean = displayedItems == null
  val fullTitle = "$title (${items.size})"
}
