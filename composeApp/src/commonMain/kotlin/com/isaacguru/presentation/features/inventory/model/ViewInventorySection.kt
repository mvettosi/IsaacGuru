package com.isaacguru.presentation.features.inventory.model

data class ViewInventorySection(
    val title: String,
    val collapsed: Boolean = true,
    val items: List<ViewInventoryItem>
) {
  val fullTitle = if (collapsed) "▸$title (${items.size})◂" else "▾$title (${items.size})▾"
}
