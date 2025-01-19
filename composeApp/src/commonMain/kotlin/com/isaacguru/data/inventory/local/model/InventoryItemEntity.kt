package com.isaacguru.data.inventory.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class InventoryItemEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val id_raw: String? = null,
    val name: String? = null,
    val type: String? = null,
    val main_type: String? = null,
    val display_type: String? = null,
    val maxcharges: String? = null,
    val chargetype: String? = null,
    val cooldown: String? = null,
    val hidden: String? = null,
    val clean_name: String? = null,
    val description: String? = null,
    val quality: String? = null,
    val pools: String? = null,
    val tags: String? = null,
    val gfxfull: String? = null,
    val dontshow: String? = null,
    val costume: String? = null,
    val keywords: String? = null,
    val unlock_method: String? = null,
    val transformations: String? = null,
    val bot_description: String? = null,
)
