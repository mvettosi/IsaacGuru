package com.isaacguru.data.itempool.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemPoolEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val emoji: String
)
