package com.isaacguru.presentation.features.inventory.items

import com.isaacguru.domain.collectable.item.model.ItemFilters

sealed interface ItemsAction {
  data class OnLoadItems(val itemFilters: ItemFilters = ItemFilters()) : ItemsAction
}
