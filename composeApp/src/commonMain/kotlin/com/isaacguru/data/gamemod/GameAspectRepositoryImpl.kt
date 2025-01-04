package com.isaacguru.data.gamemod

import com.isaacguru.data.gamemod.default.DefaultGameDataSource
import com.isaacguru.data.gamemod.remote.mapper.toDomain
import com.isaacguru.domain.gamemod.model.GameAspects
import com.isaacguru.domain.gamemod.model.GameMod
import com.isaacguru.domain.gamemod.repository.GameAspectRepository

class GameAspectRepositoryImpl(private val defaultGameDataSource: DefaultGameDataSource) :
    GameAspectRepository {
  override suspend fun getGameAspects(mod: GameMod): GameAspects {
    TODO("Not yet implemented")
  }

  override suspend fun loadDefaultGameAspects() {
    val defaultGameAspects = defaultGameDataSource.getDefaultGameAspects().toDomain()
  }
}
