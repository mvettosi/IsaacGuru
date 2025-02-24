package com.isaacguru.presentation.shared.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import isaacguru.composeapp.generated.resources.Res
import isaacguru.composeapp.generated.resources.effect_streak

@Composable
fun AppSectionHeader(
    modifier: Modifier = Modifier,
    title: String,
    isExpanded: Boolean = false,
) {
  Box(contentAlignment = Alignment.Center, modifier = modifier) {
    ResImage(
        modifier = Modifier.fillMaxWidth().requiredHeight(70.dp),
        contentScale = ContentScale.FillBounds,
        resource = Res.drawable.effect_streak,
        contentDescription = "Effect Streak")
    BrandText(
        text = if (isExpanded) "▾$title▾" else "▸$title◂",
        style = MaterialTheme.typography.headlineMedium,
        modifier = Modifier.offset(y = (-8).dp))
  }
}
