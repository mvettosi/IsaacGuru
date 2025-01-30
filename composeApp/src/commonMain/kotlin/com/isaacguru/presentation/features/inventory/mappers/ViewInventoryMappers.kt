package com.isaacguru.presentation.features.inventory.mappers

import com.isaacguru.domain.inventory.model.InventoryItem
import com.isaacguru.domain.inventory.model.Item
import com.isaacguru.presentation.features.inventory.model.ViewInventoryItem
import com.isaacguru.presentation.features.inventory.model.ViewInventorySection

fun List<InventoryItem>.toViewSection(collapsed: Boolean): List<ViewInventorySection> =
    groupBy { item -> item.toSectionTitle() }.map { entry ->
      val items = entry.value.map { it.toViewItem() }
      ViewInventorySection(
          title = entry.key, items = items, displayedItems = if (collapsed) null else items)
    }

fun InventoryItem.toViewItem() =
    ViewInventoryItem(
        id = id,
        name = name,
        summary = (this as? Item)?.quote,
        thumbnail = image,
    )

fun InventoryItem.toSectionTitle() =
    when (this) {
      is Item.Active, is Item.Passive -> "Items"
      else -> "${this::class.simpleName}s"
    }
