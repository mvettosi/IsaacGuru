package com.isaacguru.domain.gamemod.usecase

import com.isaacguru.domain.gamemod.repository.GameAspectRepository
import com.isaacguru.domain.gamemod.repository.GameModRepository
import com.isaacguru.domain.settings.usecase.GetCurrentSettingsUseCase
import com.isaacguru.domain.settings.usecase.UpdateSettingsUseCase
import kotlinx.coroutines.flow.first

class LoadDefaultModUseCase(
    private val gameModRepository: GameModRepository,
    private val gameAspectRepository: GameAspectRepository,
    private val getCurrentSettingsUseCase: GetCurrentSettingsUseCase,
    private val updateSettingsUseCase: UpdateSettingsUseCase
) {
  suspend operator fun invoke() = runCatching {
    gameAspectRepository.loadDefaultGameAspects()

    val currentSettings = getCurrentSettingsUseCase().getOrThrow().first()
    updateSettingsUseCase(currentSettings.copy(mod = gameModRepository.getDefaultGameMod()))
  }
}
