@file:OptIn(ExperimentalResourceApi::class)

package com.isaacguru.presentation.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import coil3.compose.rememberAsyncImagePainter
import com.isaacguru.presentation.shared.BottomBarBackgroundColor
import com.isaacguru.presentation.shared.SeparatorColor
import isaacguru.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi

data class BottomBarItem(
    val screen: Screen,
    val title: String,
    val sprite: String,
)

@Composable
fun BottomBar(navController: NavHostController) {
  val bottomBarItems =
      listOf(
          BottomBarItem(
              screen = Screen.Inventory,
              title = "Inventory",
              sprite = Res.getUri("files/collectibles_021_thecompass.png"),
          ),
          //          BottomBarItem(
          //              screen = Screen.More,
          //              title = "More",
          //              sprite = Res.getUri("files/collectibles_362_lilchest.png"),
          //          ),
          BottomBarItem(
              screen = Screen.Settings,
              title = "Settings",
              sprite = Res.getUri("files/collectibles_189_smbsuperfan.png"),
          ),
      )
  var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

  Column {
    HorizontalDivider(color = SeparatorColor, thickness = 2.dp)
    NavigationBar(containerColor = BottomBarBackgroundColor) {
      bottomBarItems.forEachIndexed { index, bottomBarItem ->
        val selected = index == selectedItemIndex
        NavigationBarItem(
            selected = selected,
            onClick = {
              selectedItemIndex = index
              navController.navigate(bottomBarItem.screen) {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
              }
            },
            label = { Text(bottomBarItem.title) },
            colors =
                NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.surface,
                ),
            icon = {
              Image(
                  painter = rememberAsyncImagePainter(bottomBarItem.sprite),
                  contentDescription = bottomBarItem.title,
                  modifier = Modifier.requiredSize(70.dp),
                  contentScale = ContentScale.FillHeight,
                  colorFilter =
                      if (selected) null
                      else ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) }))
            },
            alwaysShowLabel = true,
        )
      }
    }
  }
}
