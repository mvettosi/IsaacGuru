package com.isaacguru.domain.inventory.model

data class Curse(
    override val id: String,
    override val rawId: String,
    override val name: String,
    override val description: String,
    override val image: String,
    override val keywords: List<String>
) : InventoryItem()
