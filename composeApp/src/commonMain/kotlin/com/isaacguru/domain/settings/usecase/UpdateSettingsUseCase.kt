package com.isaacguru.domain.settings.usecase

import com.isaacguru.domain.settings.model.Settings
import com.isaacguru.domain.settings.repository.SettingsRepository

class UpdateSettingsUseCase(private val settingsRepository: SettingsRepository) {
  suspend operator fun invoke(settings: Settings) = runCatching {
    settingsRepository.saveSettings(settings = settings)
  }
}
