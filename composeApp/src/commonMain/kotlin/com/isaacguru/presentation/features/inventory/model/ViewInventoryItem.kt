package com.isaacguru.presentation.features.inventory.model

data class ViewInventoryItem(
    val id: String = "",
    val name: String,
    val summary: String? = null,
    val thumbnail: String
)
