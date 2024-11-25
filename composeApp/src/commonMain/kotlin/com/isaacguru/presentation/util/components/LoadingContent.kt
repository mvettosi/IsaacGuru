package com.isaacguru.presentation.util.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

@Composable
fun LoadingContent(modifier: Modifier = Modifier) {
  var displaySpinner by remember { mutableStateOf(false) }
  LaunchedEffect(true) {
    delay(500)
    displaySpinner = true
  }
  Box(modifier = Modifier.fillMaxSize().then(modifier), contentAlignment = Alignment.Center) {
    if (displaySpinner) {
      CircularProgressIndicator()
    }
  }
}
