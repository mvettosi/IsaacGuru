package com.isaacguru.data.gamemod.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class RemoteGameMod(val num: String, val id: String, val name: String, val version: String)
