package com.isaacguru.presentation.shared.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import coil3.compose.rememberAsyncImagePainter
import com.isaacguru.domain.GameAspect
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.imageResource

@Composable
fun GameAspectImage(modifier: Modifier = Modifier, gameAspect: GameAspect) {
  Image(
      painter = rememberAsyncImagePainter(gameAspect.image, filterQuality = FilterQuality.None),
      contentDescription = gameAspect.name,
      modifier = modifier)
}

@Composable
fun ResImage(
    modifier: Modifier = Modifier,
    resource: DrawableResource,
    contentDescription: String?
) {
  Image(
      bitmap = imageResource(resource),
      contentDescription = contentDescription,
      modifier = modifier,
      contentScale = ContentScale.FillHeight,
      filterQuality = FilterQuality.None)
}
