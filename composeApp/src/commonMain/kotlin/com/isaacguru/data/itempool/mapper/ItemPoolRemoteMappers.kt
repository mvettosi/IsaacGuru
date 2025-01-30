package com.isaacguru.data.itempool.mapper

import com.isaacguru.data.itempool.local.model.ItemPoolEntity
import com.isaacguru.data.itempool.remote.model.ItemPoolRemote
import com.isaacguru.domain.itempool.model.ItemPool

fun Map.Entry<String, ItemPoolRemote>.toLocal() =
    ItemPoolEntity(
        id = key, name = value.name, description = value.description, emoji = value.emoji)

fun ItemPoolEntity.toDomain() =
    ItemPool(id = id, name = name, description = description, emoji = emoji)
