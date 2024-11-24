package com.isaacguru.data.item

import com.isaacguru.domain.collectable.item.model.Item
import com.isaacguru.domain.collectable.item.model.ItemPool
import com.isaacguru.domain.collectable.item.repository.ItemRepository
import isaacguru.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi

class ItemRepositoryImpl: ItemRepository {
  @OptIn(ExperimentalResourceApi::class)
  override suspend fun getItems(itemItemPools: List<ItemPool>): List<Item> =
    buildList {
      repeat(100) {
        add(Item.Passive(
          id = "passive123",
          name = "Sample Passive",
          description = "This is a sample passive item.",
          image = Res.getUri("files/Isaac.webp"),
          keywords = listOf("keyword1", "keyword2"),
          quote = "A sample quote for this passive.",
          unlockMethod = "Unlocked by completing a challenge.",
          costume = "costumeName",
          itemPools = emptyList(),
          quality = 3,
          transformations = emptyList()
        ))
      }
    }
}