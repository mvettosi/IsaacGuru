package com.isaacguru.presentation.features.inventory.items.mapper

import com.isaacguru.domain.collectable.item.model.Item
import com.isaacguru.presentation.features.inventory.components.model.InventoryItem
import com.isaacguru.presentation.navigation.Screen

fun Item.toViewItem() =
    InventoryItem(
        id = id,
        name = name,
        summary = quote,
        thumbnail = image,
        destination = Screen.Inventory.Detail.Item(id = id))
