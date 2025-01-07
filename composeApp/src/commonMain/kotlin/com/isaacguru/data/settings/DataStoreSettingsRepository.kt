package com.isaacguru.data.settings

import com.isaacguru.domain.settings.model.Settings
import com.isaacguru.domain.settings.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DataStoreSettingsRepository : SettingsRepository {
  override fun getCurrentSettings(): Flow<Settings> {
    // TODO("Not yet implemented")
    return flow { emit(Settings()) }
  }

  override suspend fun saveSettings(settings: Settings) {
    // TODO("Not yet implemented")
  }
}
