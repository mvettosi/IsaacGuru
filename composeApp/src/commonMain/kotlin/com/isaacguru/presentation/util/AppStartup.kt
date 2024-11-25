package com.isaacguru.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import co.touchlab.kermit.Logger
import co.touchlab.kermit.platformLogWriter

@Composable
fun AppStartup() {
  LaunchedEffect(true) {
    // Perform app startup logic here
    setupLogger()
  }
}

fun setupLogger() {
  Logger.setLogWriters(platformLogWriter())
  Logger.setTag("IsaacGuru")
  Logger.i { "Logger initialized" }
}
