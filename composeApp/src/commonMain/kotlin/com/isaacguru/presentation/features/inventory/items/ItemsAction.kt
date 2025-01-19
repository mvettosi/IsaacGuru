package com.isaacguru.presentation.features.inventory.items

import com.isaacguru.domain.inventory.model.ItemFilters

sealed interface ItemsAction {
  data class OnLoadItems(val itemFilters: ItemFilters = ItemFilters()) : ItemsAction
}
