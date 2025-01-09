package com.isaacguru.presentation.features.inventory.items

import com.isaacguru.domain.collectable.item.model.ItemFilters
import com.isaacguru.presentation.features.inventory.components.model.InventoryItem
import pro.respawn.flowmvi.api.MVIAction
import pro.respawn.flowmvi.api.MVIIntent
import pro.respawn.flowmvi.api.MVIState

data class ItemsState(
    val items: List<InventoryItem> = emptyList(),
    val itemFilters: ItemFilters = ItemFilters(),
    val isLoading: Boolean = true,
    val error: Throwable? = null
) : MVIState

sealed interface ItemsIntent : MVIIntent {
  data object LoadItems : ItemsIntent
}

sealed interface ItemsAction : MVIAction
