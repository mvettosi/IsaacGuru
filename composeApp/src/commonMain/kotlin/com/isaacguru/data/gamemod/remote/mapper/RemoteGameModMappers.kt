package com.isaacguru.data.gamemod.remote.mapper

import com.isaacguru.data.gamemod.remote.model.RemoteGameMod
import com.isaacguru.domain.gamemod.model.GameMod

fun RemoteGameMod.toDomain(): GameMod =
    GameMod(id = id, name = name, version = version, versionCode = 0 // TODO
        )
