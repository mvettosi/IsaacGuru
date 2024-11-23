package com.isaacguru.domain.character

import com.isaacguru.domain.GameAspect

data class Character(
    override val id: String,
    override val name: String,
    override val description: String,
    override val image: String,
    override val keywords: List<String>,
    val costume: String,
    val unlockMethod: String?
) : GameAspect()
