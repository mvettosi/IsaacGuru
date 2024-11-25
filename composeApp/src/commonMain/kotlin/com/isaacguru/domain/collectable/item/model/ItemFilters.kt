package com.isaacguru.domain.collectable.item.model

import kotlin.jvm.JvmInline

data class ItemFilters(val itemPools: List<ItemPool> = emptyList(), val itemType: ItemType? = null)

@JvmInline value class ItemPool(private val value: String)

enum class ItemType {
  ACTIVE,
  PASSIVE
}
