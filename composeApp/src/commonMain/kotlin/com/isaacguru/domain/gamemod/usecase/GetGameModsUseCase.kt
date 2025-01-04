package com.isaacguru.domain.gamemod.usecase

import com.isaacguru.domain.gamemod.repository.GameModRepository

class GetGameModsUseCase(private val gameModRepository: GameModRepository) {
  suspend operator fun invoke() = runCatching { gameModRepository.getGameMods() }
}
