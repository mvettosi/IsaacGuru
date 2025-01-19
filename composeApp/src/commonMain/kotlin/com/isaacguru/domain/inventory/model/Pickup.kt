package com.isaacguru.domain.inventory.model

data class Pickup(
    override val id: String,
    override val name: String,
    override val description: String,
    override val image: String,
    override val keywords: List<String>,
    override val unlockMethod: String?
) : Collectible()
