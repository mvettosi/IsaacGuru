package com.isaacguru.presentation.features.inventory.items

import com.isaacguru.domain.inventory.model.ItemFilters
import com.isaacguru.presentation.features.inventory.components.model.ViewInventoryItem

data class ItemsViewState(
    val items: List<ViewInventoryItem> = emptyList(),
    val itemFilters: ItemFilters = ItemFilters(),
    val isLoading: Boolean = true
)
