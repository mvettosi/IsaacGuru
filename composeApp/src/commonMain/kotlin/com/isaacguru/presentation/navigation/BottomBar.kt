@file:OptIn(ExperimentalResourceApi::class)

package com.isaacguru.presentation.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.rememberAsyncImagePainter
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
              screen = Screen.Inventory.Home,
              title = "Inventory",
              sprite = Res.getUri("files/collectibles_021_thecompass.png"),
          ),
          BottomBarItem(
              screen = Screen.Search,
              title = "Search",
              sprite = Res.getUri("files/collectibles_005_myreflection.png"),
          ),
          BottomBarItem(
              screen = Screen.Settings,
              title = "Settings",
              sprite = Res.getUri("files/collectibles_189_smbsuperfan.png"),
          ),
      )
  var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

  Column {
    HorizontalDivider(color = SeparatorColor, thickness = 2.dp)
    NavigationBar(containerColor = Color.Transparent) {
      bottomBarItems.forEachIndexed { index, bottomBarItem ->
        val selected = index == selectedItemIndex
        NavigationBarItem(
            selected = selected,
            onClick = {
              selectedItemIndex = index
              navController.navigate(bottomBarItem.screen)
            },
            label = { Text(bottomBarItem.title) },
            icon = {
              Image(
                  painter = rememberAsyncImagePainter(bottomBarItem.sprite),
                  contentDescription = bottomBarItem.title,
                  modifier = Modifier.requiredSize(50.dp),
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
