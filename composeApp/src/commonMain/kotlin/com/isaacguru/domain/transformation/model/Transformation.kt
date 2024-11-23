package com.isaacguru.domain.transformation.model

import com.isaacguru.domain.GameAspect

data class Transformation(
    override val id: String,
    override val name: String,
    override val description: String,
    override val image: String,
    override val keywords: List<String>,
    val costume: String,
) : GameAspect()
