package com.isaacguru.data.gamemod.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class RemoteGameAspects(
    val item: List<RemoteData>,
    val trinket: List<RemoteData>,
    val pickup: List<RemoteData>,
    val consumable: List<RemoteData>,
    val machine: List<RemoteData>,
    val character: List<RemoteData>,
    val transformation: List<RemoteData>,
    val curse: List<RemoteData>,
)
