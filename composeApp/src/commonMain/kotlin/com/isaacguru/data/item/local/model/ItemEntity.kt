package com.isaacguru.data.item.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.isaacguru.domain.collectable.item.model.Item.Active.ChargeType
import com.isaacguru.domain.collectable.item.model.ItemType

@Entity
data class ItemEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val name: String,
    val description: String,
    val image: String,
    val keywords: List<String>,
    val quote: String? = null,
    val unlockMethod: String? = null,
    val costume: String,
    val itemPools: List<String>,
    val quality: Int,
    val transformations: List<String>,
    val type: ItemType,
    val maxCharges: Int? = null,
    val chargeType: ChargeType? = null,
    val cooldown: Int? = null,
)
