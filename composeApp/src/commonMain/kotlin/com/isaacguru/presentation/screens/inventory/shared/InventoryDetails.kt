package com.isaacguru.presentation.screens.inventory.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.isaacguru.presentation.screens.inventory.shared.components.InventoryTopBar
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun InventoryDetails(
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null,
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
  Column(modifier = modifier.background(MaterialTheme.colorScheme.background)) {
    InventoryTopBar(
        title = "Item Details",
        onBackClick = onBackClick,
    )
    Text(
        text = title,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth(),
        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
    content()
  }
}

@Preview
@Composable
fun InventoryDetailsPreview() {
  InventoryDetails(
      title = "Item Title",
      onBackClick = { /*TODO*/},
  ) { Text("Item Content") }
}
