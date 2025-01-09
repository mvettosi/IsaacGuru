package com.isaacguru.presentation.features.inventory.items

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.isaacguru.di.storeViewModel
import com.isaacguru.presentation.features.inventory.components.InventoryScreen
import com.isaacguru.presentation.features.inventory.components.model.InventoryItem
import pro.respawn.flowmvi.api.Store
import pro.respawn.flowmvi.compose.dsl.subscribe

@Composable
fun ItemsScreen(
    store: Store<ItemsState, ItemsIntent, ItemsAction> =
        storeViewModel<ItemsContainer, ItemsState, ItemsIntent, ItemsAction>(),
    onBackClick: () -> Unit,
    displayItemDetails: (InventoryItem) -> Unit
) =
    with(store) {
      val viewState by subscribe {}

      InventoryScreen(
          title = "Items",
          onBackClick = onBackClick,
          defaultToList = false,
          isLoading = viewState.isLoading,
          inventoryItems = viewState.items,
          onInventoryItemClick = displayItemDetails)
    }
