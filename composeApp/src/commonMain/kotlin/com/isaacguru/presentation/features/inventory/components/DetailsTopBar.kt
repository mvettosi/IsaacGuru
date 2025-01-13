package com.isaacguru.presentation.features.inventory.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.isaacguru.domain.GameAspect
import com.isaacguru.domain.collectable.item.model.Item
import com.isaacguru.presentation.shared.ItemIdColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopBar(
    gameAspect: GameAspect? = null,
    onBackClick: (() -> Unit)? = null,
) {
  CenterAlignedTopAppBar(
      windowInsets = WindowInsets(0.dp),
      colors =
          TopAppBarDefaults.centerAlignedTopAppBarColors().copy(containerColor = Color.Transparent),
      navigationIcon = {
        if (onBackClick != null) {
          IconButton(onClick = onBackClick) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
          }
        }
      },
      title = {
        gameAspect?.let {
          Box(
              modifier =
                  Modifier.background(
                          color = gameAspect.getBadgeColour(), shape = RoundedCornerShape(4.dp))
                      .padding(vertical = 4.dp, horizontal = 10.dp)) {
            Text(
                text = "#${gameAspect.id}",
                style = MaterialTheme.typography.headlineSmall.copy(Color.White),
                modifier = Modifier,
            )
          }
        }
      },
  )
}

private fun GameAspect.getBadgeColour() =
    when (this) {
      is Item -> ItemIdColor
      else -> Color.White
    }
