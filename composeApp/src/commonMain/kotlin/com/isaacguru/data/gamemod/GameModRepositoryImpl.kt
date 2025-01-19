package com.isaacguru.data.gamemod

import com.isaacguru.data.gamemod.remote.GameModRemoteDataSource
import com.isaacguru.data.gamemod.remote.mapper.toDomain
import com.isaacguru.domain.gamemod.model.GameMod
import com.isaacguru.domain.gamemod.repository.GameModRepository

class GameModRepositoryImpl(val gameModRemoteDataSource: GameModRemoteDataSource) :
    GameModRepository {
  override suspend fun getGameMods(refresh: Boolean): List<GameMod> {
    TODO("Not yet implemented")
  }

  override suspend fun getDefaultGameMod(): GameMod =
      gameModRemoteDataSource.getDefaultGameMod().toDomain()
}
