package com.isaacguru.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.isaacguru.presentation.navigation.BottomBar
import com.isaacguru.presentation.navigation.NavigationGraph
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App(appViewModel: AppViewModel = koinViewModel()) {
  MaterialTheme {
    LaunchedEffect(Unit) { appViewModel.appStartup() }

    val navController = rememberNavController()
    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = { BottomBar(navController) }) {
        innerPadding ->
      NavigationGraph(navController = navController, modifier = Modifier.padding(innerPadding))
    }
  }
}
