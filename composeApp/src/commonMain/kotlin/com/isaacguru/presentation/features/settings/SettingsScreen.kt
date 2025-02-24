package com.isaacguru.presentation.features.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.isaacguru.presentation.shared.components.AppSwitch
import com.isaacguru.presentation.shared.components.CollapsableSection

@Composable
fun SettingsScreen() {
  Scaffold(
      containerColor = Color.Transparent,
      contentColor = MaterialTheme.colorScheme.onSurface,
  ) { padding ->
    var allowAnalytics by remember { mutableStateOf(true) }

    Column(modifier = Modifier.padding(padding).fillMaxSize()) {
      SettingSection(title = "Privacy") {
        TogglableSetting(
            title = "Allow crash reports and analytics",
            enabled = allowAnalytics,
            onToggled = { enabled -> allowAnalytics = enabled })
      }
    }
  }
}

@Composable
fun SettingSection(modifier: Modifier = Modifier, title: String, content: @Composable () -> Unit) {
  CollapsableSection(modifier = modifier, title = title, content = content)
}

@Composable
fun TogglableSetting(enabled: Boolean, onToggled: (Boolean) -> Unit, title: String) {
  val interactionSource = remember { MutableInteractionSource() }
  fun handleToggle() {
    onToggled(!enabled)
  }
  Row(
      verticalAlignment = Alignment.CenterVertically,
      modifier =
          Modifier.clickable(
                  interactionSource = interactionSource,
                  onClick = { handleToggle() },
                  indication = ripple())
              .fillMaxWidth()
              .padding(horizontal = 16.dp, vertical = 12.dp),
      horizontalArrangement = Arrangement.spacedBy(30.dp)) {
    Text(text = title, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.weight(1f))
      AppSwitch(
        interactionSource = interactionSource,
        checked = enabled,
        onCheckedChange = { handleToggle() })
  }
}

@Composable
fun CategoryItem(title: String, icon: ImageVector, onClick: () -> Unit) {
  Surface(
      onClick = onClick,
      shape = MaterialTheme.shapes.medium,
  ) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(30.dp)) {
      Icon(
          icon,
          contentDescription = null,
          modifier = Modifier.size(28.dp),
          tint = MaterialTheme.colorScheme.onSurface)
      Text(title, style = MaterialTheme.typography.bodyLarge)
    }
  }
}

@Composable
fun AppVersion(versionText: String, copyrights: String, onClick: () -> Unit) {
  Surface(onClick = onClick, color = Color.Transparent) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(30.dp)) {
      Box(
          modifier = Modifier.size(30.dp),
      )

      Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
            versionText,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(0.44f))
        Text(
            copyrights,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(0.44f))
      }
    }
  }
}
