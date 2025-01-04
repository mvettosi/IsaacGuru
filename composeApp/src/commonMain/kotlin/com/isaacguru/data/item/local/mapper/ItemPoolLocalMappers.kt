package com.isaacguru.data.item.local.mapper

import com.isaacguru.data.item.local.model.ItemPoolEntity
import com.isaacguru.domain.collectable.item.model.ItemPool

fun ItemPool.toEntity() = ItemPoolEntity(name = name, description = description)

fun ItemPoolEntity.toDomain() = ItemPool(name = name, description = description)
