package com.isaacguru.presentation.screens.inventory.shared.model

import com.isaacguru.presentation.navigation.Screen

data class InventoryItem(
    val id: String = "",
    val name: String,
    val thumbnail: String,
    val destination: Screen? = null
)
