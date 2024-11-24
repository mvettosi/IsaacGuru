package com.isaacguru.presentation.features.inventory.items

import com.isaacguru.domain.collectable.item.model.ItemPool
import com.isaacguru.presentation.features.inventory.components.model.InventoryItem

data class ItemsViewState(
  val items: List<InventoryItem> = emptyList(),
  val itemPools: List<ItemPool> = emptyList(),
  val isLoading: Boolean = false
)