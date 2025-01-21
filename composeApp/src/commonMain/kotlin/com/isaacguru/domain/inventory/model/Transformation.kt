package com.isaacguru.domain.inventory.model

data class Transformation(
    override val id: String,
    override val rawId: String,
    override val name: String,
    override val description: String,
    override val image: String,
    override val keywords: List<String>,
    val costume: String,
) : InventoryItem()
