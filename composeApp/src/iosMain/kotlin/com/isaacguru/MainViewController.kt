package com.isaacguru

import androidx.compose.ui.window.ComposeUIViewController
import com.isaacguru.presentation.App
import com.isaacguru.presentation.di.initKoin

fun MainViewController() = ComposeUIViewController(
  configure = {
    initKoin()
  }
) { App() }
