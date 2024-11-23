package com.isaacguru.domain.collectable.pickup.model

import com.isaacguru.domain.collectable.Collectible

data class Pickup(
    override val id: String,
    override val name: String,
    override val description: String,
    override val image: String,
    override val keywords: List<String>,
    override val unlockMethod: String?
) : Collectible()
