package com.isaacguru.domain.gamemod.usecase

import com.isaacguru.domain.gamemod.repository.GameModRepository
import com.isaacguru.domain.inventory.repository.InventoryRepository
import com.isaacguru.domain.settings.usecase.GetCurrentSettingsUseCase
import com.isaacguru.domain.settings.usecase.UpdateSettingsUseCase
import kotlinx.coroutines.flow.first

class LoadDefaultInventoryUseCase(
    private val gameModRepository: GameModRepository,
    private val inventoryRepository: InventoryRepository,
    private val getCurrentSettingsUseCase: GetCurrentSettingsUseCase,
    private val updateSettingsUseCase: UpdateSettingsUseCase,
    private val setInventoryUseCase: SetInventoryUseCase
) {
  suspend operator fun invoke() = runCatching {
    //    val inventory = inventoryRepository.ge().first()
    //    setInventoryUseCase(inventory).getOrThrow()

    val currentSettings = getCurrentSettingsUseCase().getOrThrow().first()
    updateSettingsUseCase(currentSettings.copy(mod = gameModRepository.getDefaultGameMod()))
  }
}
