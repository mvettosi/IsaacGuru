package com.isaacguru

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.isaacguru.presentation.App

fun main() = application {
  Window(
      onCloseRequest = ::exitApplication,
      title = "IsaacGuru",
  ) { App() }
}
