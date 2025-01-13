package com.isaacguru.presentation.features.inventory.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.isaacguru.presentation.shared.SeparatorColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryTopBar(
    title: String,
    onBackClick: (() -> Unit)? = null,
    onFilterClick: (() -> Unit)? = null,
    onListClick: (() -> Unit)? = null
) {
  Column {
    TopAppBar(
        windowInsets = WindowInsets(0.dp),
        colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = Color.Transparent),
        navigationIcon = {
          if (onBackClick != null) {
            IconButton(onClick = onBackClick) {
              Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
          }
        },
        title = { Text(title) },
        actions = {
          if (onFilterClick != null) {
            IconButton(onClick = onFilterClick) {
              Icon(imageVector = Icons.Default.Edit, contentDescription = "Filter")
            }
          }
          if (onListClick != null) {
            IconButton(onClick = onListClick) {
              Icon(
                  imageVector = Icons.AutoMirrored.Filled.List,
                  contentDescription = "Toggle list and grid view")
            }
          }
        })
    HorizontalDivider(color = SeparatorColor, thickness = 2.dp)
  }
}
