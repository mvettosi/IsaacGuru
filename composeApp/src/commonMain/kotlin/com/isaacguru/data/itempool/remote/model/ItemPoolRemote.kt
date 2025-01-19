package com.isaacguru.data.itempool.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class ItemPoolRemote(
    val name: String,
    val description: String,
    val x: String,
    val y: String,
    val emoji: String,
    val mod: String
)
