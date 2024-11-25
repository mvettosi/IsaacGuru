package com.isaacguru.presentation.features.inventory.items

import com.isaacguru.domain.collectable.item.model.ItemFilters
import com.isaacguru.presentation.features.inventory.components.model.InventoryItem

data class ItemsViewState(
    val items: List<InventoryItem> = emptyList(),
    val itemFilters: ItemFilters = ItemFilters(),
    val isLoading: Boolean = true
)
