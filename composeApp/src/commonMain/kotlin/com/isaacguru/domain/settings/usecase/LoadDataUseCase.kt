package com.isaacguru.domain.settings.usecase

import co.touchlab.kermit.Logger
import com.isaacguru.domain.device.usecase.IsNetworkAvailableUseCase
import com.isaacguru.domain.gamemod.usecase.GetGameModsUseCase
import com.isaacguru.domain.gamemod.usecase.LoadDefaultInventoryUseCase
import com.isaacguru.domain.gamemod.usecase.SetGameModUseCase
import kotlinx.coroutines.flow.firstOrNull

class LoadDataUseCase(
    private val getCurrentSettingsUseCase: GetCurrentSettingsUseCase,
    private val loadDefaultInventoryUseCase: LoadDefaultInventoryUseCase,
    private val isNetworkAvailableUseCase: IsNetworkAvailableUseCase,
    private val getGameModsUseCase: GetGameModsUseCase,
    private val setGameModUseCase: SetGameModUseCase
) {
  suspend operator fun invoke() = runCatching {
    val currentMod = getCurrentSettingsUseCase().getOrThrow().firstOrNull()?.mod

    if (currentMod == null) {
      Logger.d { "No mods found, initialising" }
      loadDefaultInventoryUseCase().getOrThrow()
      Logger.d { "Default database initialised" }
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
