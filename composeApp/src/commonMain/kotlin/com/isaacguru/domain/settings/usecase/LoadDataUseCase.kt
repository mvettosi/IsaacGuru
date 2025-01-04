package com.isaacguru.domain.settings.usecase

import com.isaacguru.domain.device.usecase.IsNetworkAvailableUseCase
import com.isaacguru.domain.gamemod.usecase.GetGameModsUseCase
import com.isaacguru.domain.gamemod.usecase.LoadDefaultModUseCase
import com.isaacguru.domain.gamemod.usecase.SetGameModUseCase
import kotlinx.coroutines.flow.firstOrNull

class LoadDataUseCase(
    private val getCurrentSettingsUseCase: GetCurrentSettingsUseCase,
    private val loadDefaultModUseCase: LoadDefaultModUseCase,
    private val isNetworkAvailableUseCase: IsNetworkAvailableUseCase,
    private val getGameModsUseCase: GetGameModsUseCase,
    private val setGameModUseCase: SetGameModUseCase
) {
  suspend operator fun invoke() = runCatching {
    val currentMod = getCurrentSettingsUseCase().getOrThrow().firstOrNull()?.mod

    if (currentMod == null) {
      loadDefaultModUseCase().getOrThrow()
    }

    if (isNetworkAvailableUseCase().getOrThrow()) {
      val currentVersionCode = currentMod?.versionCode ?: 0
      val updatedGameMod = getGameModsUseCase().getOrNull()?.firstOrNull { it.id == currentMod?.id }
      if (updatedGameMod != null && updatedGameMod.versionCode > currentVersionCode) {
        setGameModUseCase(updatedGameMod).getOrThrow()
      }
    }
  }
}
