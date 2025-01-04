package com.isaacguru.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import co.touchlab.kermit.platformLogWriter
import kotlinx.coroutines.launch

class AppViewModel(
//  private val loadDataUseCase: LoadDataUseCase
) : ViewModel() {
  fun appStartup() {
    viewModelScope.launch {
      // Perform app startup logic here
      setupLogger()
      //      loadDataUseCase()
    }
  }

  private fun setupLogger() {
    Logger.setLogWriters(platformLogWriter())
    Logger.setTag("IsaacGuru")
    Logger.i { "Logger initialized" }
  }
}
