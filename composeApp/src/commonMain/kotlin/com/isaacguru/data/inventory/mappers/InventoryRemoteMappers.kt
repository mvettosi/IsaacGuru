package com.isaacguru.data.inventory.mappers

import com.isaacguru.data.inventory.local.model.InventoryItemEntity
import com.isaacguru.data.inventory.remote.model.InventoryItemRemote
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
fun InventoryItemRemote.toLocal(): InventoryItemEntity? =
    if (this.dontshow == "1") null
    else
        InventoryItemEntity(
            id = this.id ?: Uuid.random().toString(),
            id_raw = this.id_raw,
            name = this.name,
            type = this.type,
            main_type = this.main_type,
            display_type = this.display_type,
            maxcharges = this.maxcharges,
            chargetype = this.chargetype,
            cooldown = this.cooldown,
            hidden = this.hidden,
            clean_name = this.clean_name,
            description = this.description,
            quality = this.quality,
            pools = this.pools,
            tags = this.tags,
            gfxfull = this.gfxfull,
            dontshow = this.dontshow,
            costume = this.costume,
            keywords = this.keywords,
            unlock_method = this.unlock_method,
            transformations = this.transformations,
            bot_description = this.bot_description)
