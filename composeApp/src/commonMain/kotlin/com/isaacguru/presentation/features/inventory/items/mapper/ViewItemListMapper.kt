package com.isaacguru.presentation.features.inventory.items.mapper

import com.isaacguru.domain.collectable.item.model.Item
import com.isaacguru.presentation.features.inventory.components.model.InventoryItem
import com.isaacguru.presentation.navigation.Screen
import com.isaacguru.presentation.util.ViewMapper

class ViewItemListMapper: ViewMapper<Item, InventoryItem> {
  override fun mapToView(domain: Item) = InventoryItem(
    id = domain.id,
    name = domain.name,
    summary = domain.quote,
    thumbnail = domain.image,
    destination = Screen.Inventory.Detail.Item(id = domain.id)
  )
}