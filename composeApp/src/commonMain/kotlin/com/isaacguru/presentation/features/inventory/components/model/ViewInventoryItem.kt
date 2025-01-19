package com.isaacguru.presentation.features.inventory.components.model

import com.isaacguru.presentation.navigation.Screen

data class ViewInventoryItem(
    val id: String = "",
    val name: String,
    val summary: String? = null,
    val thumbnail: String,
    val destination: Screen? = null
)
