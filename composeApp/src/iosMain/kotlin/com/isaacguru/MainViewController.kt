package com.isaacguru

import androidx.compose.ui.window.ComposeUIViewController
import com.isaacguru.di.initKoin
import com.isaacguru.presentation.App

fun MainViewController() = ComposeUIViewController(configure = { initKoin() }) { App() }
