package com.isaacguru.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.compose.rememberNavController
import com.isaacguru.presentation.navigation.BottomBar
import com.isaacguru.presentation.navigation.NavigationGraph
import com.isaacguru.presentation.shared.appColorScheme
import isaacguru.composeapp.generated.resources.Res
import isaacguru.composeapp.generated.resources.background_sheol
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App(appViewModel: AppViewModel = koinViewModel()) {
  MaterialTheme(colorScheme = appColorScheme) {
    LaunchedEffect(Unit) { appViewModel.appStartup() }

    val navController = rememberNavController()
    Box {
      Image(
          modifier = Modifier.fillMaxSize(),
          painter = painterResource(Res.drawable.background_sheol),
          contentDescription = "background_image",
          contentScale = ContentScale.FillBounds)
      Scaffold(
          modifier = Modifier.fillMaxSize(),
          containerColor = Color.Transparent,
          bottomBar = { BottomBar(navController) }) { innerPadding ->
        NavigationGraph(navController = navController, modifier = Modifier.padding(innerPadding))
      }
    }
  }
}
