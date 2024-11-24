package com.isaacguru.presentation.features.inventory.items

import com.isaacguru.domain.collectable.item.model.ItemPool

sealed interface ItemsAction {
  data class OnLoadItems(val itemPools: List<ItemPool> = emptyList()) : ItemsAction
}
