package com.isaacguru.presentation.features.inventory.mappers

import com.isaacguru.domain.inventory.model.ItemFilters
import com.isaacguru.domain.itempool.model.ItemPool
import com.isaacguru.presentation.features.inventory.model.FilterOption
import com.isaacguru.presentation.features.inventory.model.FilterSection
import com.isaacguru.presentation.features.inventory.model.FilterSections
import com.isaacguru.presentation.shared.fromDiscordString

fun ItemPool.toFilterOption(): FilterOption =
    FilterOption(
        id = id,
        label = "$emoji $name".fromDiscordString(),
    )

fun FilterSections.toItemFilters(query: String): ItemFilters =
    ItemFilters(
        query = query,
        itemPools = get(FilterSection.ITEM_POOLS)?.filter { it.selected }?.map { it.id }
                ?: emptyList())
