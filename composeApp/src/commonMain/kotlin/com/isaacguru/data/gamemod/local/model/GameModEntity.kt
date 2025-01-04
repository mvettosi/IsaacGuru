package com.isaacguru.data.gamemod.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GameModEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val name: String,
    val version: String,
    val versionCode: Long,
)
