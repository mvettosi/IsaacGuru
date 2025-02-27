package com.isaacguru.presentation.shared.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun AppSwitch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    thumbContent: (@Composable () -> Unit)? = null,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
) {
  Switch(
      checked = checked,
      onCheckedChange = onCheckedChange,
      modifier = modifier,
      thumbContent = thumbContent,
      enabled = enabled,
      interactionSource = interactionSource,
      colors =
          SwitchDefaults.colors(
              checkedThumbColor = Color.Black,
              checkedTrackColor = Color.White,
              checkedBorderColor = Color.White,
              checkedIconColor = Color.Black,
              uncheckedThumbColor = Color.White,
              uncheckedTrackColor = Color.Black,
              uncheckedBorderColor = Color.White,
              uncheckedIconColor = Color.Black,
          ))
}
