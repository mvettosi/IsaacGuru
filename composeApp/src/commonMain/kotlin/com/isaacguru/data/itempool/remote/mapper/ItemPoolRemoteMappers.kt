package com.isaacguru.data.itempool.remote.mapper

import com.isaacguru.data.itempool.remote.model.ItemPoolRemote
import com.isaacguru.domain.itempool.model.ItemPool

fun Map.Entry<String, ItemPoolRemote>.toDomain() =
    ItemPool(id = key, name = value.name, description = value.description)
