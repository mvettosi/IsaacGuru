package com.isaacguru.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController

data class BottomBarItem(
    val screen: Screen,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

@Composable
fun BottomBar(navController: NavHostController) {
  val bottomBarItems =
      listOf(
          BottomBarItem(
              screen = Screen.Inventory.Home,
              title = "Inventory",
              selectedIcon = Icons.Filled.Menu,
              unselectedIcon = Icons.Outlined.Menu,
          ),
          BottomBarItem(
              screen = Screen.Search,
              title = "Search",
              selectedIcon = Icons.Filled.Search,
              unselectedIcon = Icons.Outlined.Search,
          ),
          BottomBarItem(
              screen = Screen.Settings,
              title = "Settings",
              selectedIcon = Icons.Filled.Settings,
              unselectedIcon = Icons.Outlined.Settings,
          ),
      )
  var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

  NavigationBar {
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
            Icon(
                imageVector =
                    if (selected) bottomBarItem.selectedIcon else bottomBarItem.unselectedIcon,
                contentDescription = bottomBarItem.title)
          },
          alwaysShowLabel = true,
      )
    }
  }
}
