package com.isaacguru.presentation.features.inventory.items.mapper

import com.isaacguru.domain.inventory.model.InventoryItem
import com.isaacguru.domain.inventory.model.Item
import com.isaacguru.presentation.features.inventory.components.model.ViewInventoryItem
import com.isaacguru.presentation.navigation.Screen

fun InventoryItem.toViewItem() =
    ViewInventoryItem(
        id = id,
        name = name,
        summary = (this as? Item)?.quote,
        thumbnail = image,
        destination = Screen.Inventory.Detail.Item(id = id))
