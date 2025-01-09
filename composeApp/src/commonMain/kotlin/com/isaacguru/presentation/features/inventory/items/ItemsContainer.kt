package com.isaacguru.presentation.features.inventory.items

import co.touchlab.kermit.Logger
import com.isaacguru.domain.collectable.item.usecase.GetItemsUseCase
import com.isaacguru.presentation.features.inventory.items.mapper.toViewItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import pro.respawn.flowmvi.api.Container
import pro.respawn.flowmvi.api.PipelineContext
import pro.respawn.flowmvi.dsl.store
import pro.respawn.flowmvi.plugins.init
import pro.respawn.flowmvi.plugins.recover
import pro.respawn.flowmvi.plugins.reduce

private typealias Ctx = PipelineContext<ItemsState, ItemsIntent, ItemsAction>

class ItemsContainer(private val getItemsUseCase: GetItemsUseCase) :
    Container<ItemsState, ItemsIntent, ItemsAction> {

  override val store =
      store(initial = ItemsState()) {
        configure { name = "Items" }
        recover {
          updateState { copy(error = it) }
          null
        }
        reduce { intent ->
          when (intent) {
            ItemsIntent.LoadItems -> loadItems()
          }
        }
        init { loadItems() }
      }

  private suspend fun Ctx.loadItems() {
    updateState {
      withContext(Dispatchers.IO) {
        getItemsUseCase(itemFilters)
            .fold(
                onSuccess = { items ->
                  copy(items = items.map { item -> item.toViewItem() }, isLoading = false)
                },
                onFailure = {
                  Logger.e(it) { "Error getting items" }
                  copy(items = emptyList(), isLoading = false, error = it)
                })
      }
    }
  }
}
