package com.isaacguru.presentation.features.inventory

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.isaacguru.presentation.features.inventory.model.ViewInventorySection
import com.isaacguru.presentation.shared.ObserveEvents
import com.isaacguru.presentation.shared.components.BrandText
import com.isaacguru.presentation.shared.components.ResImage
import isaacguru.composeapp.generated.resources.Res
import isaacguru.composeapp.generated.resources.effect_streak
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun InventoryScreenRoot(
    viewModel: InventoryViewModel = koinViewModel(),
    onBackClick: () -> Unit,
    onNavigateToDetail: (id: String) -> Unit
) {
  val viewState by viewModel.viewState.collectAsStateWithLifecycle()

  ObserveEvents(viewModel.action) {
    when (it) {
      InventoryAction.NavigateBack -> onBackClick()
      is InventoryAction.NavigateToDetail -> onNavigateToDetail(it.id)
    }
  }

  InventoryScreen(viewState = viewState, intent = viewModel::onIntent)
}

@Composable
fun InventoryScreen(
    viewState: InventoryViewState,
    intent: (InventoryIntent) -> Unit,
) {
  Surface(contentColor = MaterialTheme.colorScheme.onSurface, color = Color.Transparent) {
    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
      viewState.sections.forEach { InventorySection(section = it, intent = intent) }
    }
  }
}

@Composable
fun InventorySection(section: ViewInventorySection, intent: (InventoryIntent) -> Unit) {
  Box(
      contentAlignment = Alignment.Center,
      modifier = Modifier.clickable { intent(InventoryIntent.OnSectionClick(section.title)) }) {
    ResImage(
        modifier = Modifier.requiredHeight(60.dp),
        contentScale = ContentScale.FillHeight,
        resource = Res.drawable.effect_streak,
        contentDescription = "Effect Streak")
    BrandText(
        text = section.fullTitle,
        style = MaterialTheme.typography.displaySmall,
        modifier = Modifier.offset(y = (-8).dp))
  }
}
