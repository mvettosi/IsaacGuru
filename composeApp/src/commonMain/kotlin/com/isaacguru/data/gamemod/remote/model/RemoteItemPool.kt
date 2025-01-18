package com.isaacguru.data.gamemod.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class RemoteItemPool(
    val name: String,
    val description: String,
    val x: String,
    val y: String,
    val emoji: String,
    val mod: String
)
