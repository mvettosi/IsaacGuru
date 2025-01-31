package com.isaacguru.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import co.touchlab.kermit.platformLogWriter
import com.isaacguru.domain.settings.usecase.LoadDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class AppViewModel(private val loadDataUseCase: LoadDataUseCase) : ViewModel() {
  fun appStartup() {
    viewModelScope.launch(Dispatchers.IO) {
      // Perform app startup logic here
      setupLogger()
      loadDataUseCase().getOrElse { Logger.e(it) { "Error initialising the database" } }
    }
  }

  private fun setupLogger() {
    Logger.setLogWriters(platformLogWriter())
    Logger.setTag("IsaacGuru")
    Logger.i { "Logger initialized" }
  }
}
