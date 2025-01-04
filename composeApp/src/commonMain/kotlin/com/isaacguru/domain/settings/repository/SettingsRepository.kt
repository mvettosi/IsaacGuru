package com.isaacguru.domain.settings.repository

import com.isaacguru.domain.settings.model.Settings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
  fun getCurrentSettings(): Flow<Settings>
  suspend fun saveSettings(settings: Settings)
}
