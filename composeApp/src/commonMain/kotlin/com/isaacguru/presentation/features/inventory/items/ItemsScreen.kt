package com.isaacguru.presentation.features.inventory.items

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.isaacguru.presentation.features.inventory.components.InventoryScreen
import com.isaacguru.presentation.features.inventory.components.model.InventoryItem
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ItemsScreen(
    viewModel: ItemsViewModel = koinViewModel(),
    onBackClick: () -> Unit,
    displayItemDetails: (InventoryItem) -> Unit
) {
  val viewState by viewModel.state.collectAsStateWithLifecycle()
  InventoryScreen(
      title = "Items",
      onBackClick = onBackClick,
      defaultToList = false,
      isLoading = viewState.isLoading,
      inventoryItems = viewState.items,
      onInventoryItemClick = displayItemDetails)
}
