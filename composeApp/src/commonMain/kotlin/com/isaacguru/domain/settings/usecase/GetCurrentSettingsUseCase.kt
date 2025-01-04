package com.isaacguru.domain.settings.usecase

import com.isaacguru.domain.settings.repository.SettingsRepository

class GetCurrentSettingsUseCase(private val settingsRepository: SettingsRepository) {
  suspend operator fun invoke() = runCatching { settingsRepository.getCurrentSettings() }
}
