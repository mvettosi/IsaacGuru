package com.isaacguru.data.itempool.local.mapper

import com.isaacguru.data.itempool.local.model.ItemPoolEntity
import com.isaacguru.domain.itempool.model.ItemPool

fun ItemPool.toEntity() = ItemPoolEntity(id = id, name = name, description = description)

fun ItemPoolEntity.toDomain() = ItemPool(id = id, name = name, description = description)
