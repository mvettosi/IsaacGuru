package com.isaacguru.domain.inventory.model

data class Consumable(
    override val id: String,
    override val rawId: String,
    override val orderId: Int,
    override val name: String,
    override val description: String,
    override val image: String,
    override val keywords: List<String>,
    override val quote: String?,
    override val unlockMethod: String?,
    val typeDescription: String,
) : Collectible()
