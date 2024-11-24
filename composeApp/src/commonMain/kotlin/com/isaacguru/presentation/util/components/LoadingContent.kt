package com.isaacguru.presentation.util.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun LoadingContent(modifier: Modifier = Modifier) {
  Box(modifier = Modifier.fillMaxWidth().then(modifier), contentAlignment = Alignment.Center) {
    CircularProgressIndicator()
  }
}