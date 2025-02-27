package com.isaacguru.presentation.shared.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun CollapsableSection(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable () -> Unit
) {
  var isExpanded by rememberSaveable { mutableStateOf(true) }
  Column(
      modifier =
          modifier
              .clickable { isExpanded = !isExpanded }
              .background(color = Color.Transparent)
              .fillMaxWidth()) {
    AppSectionHeader(isExpanded = isExpanded, title = title)

    AnimatedVisibility(modifier = Modifier.fillMaxWidth(), visible = isExpanded) { content() }
  }
}
