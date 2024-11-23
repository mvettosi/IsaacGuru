package com.isaacguru.domain.machine.model

import com.isaacguru.domain.GameAspect

data class Machine(
  override val id: String,
  override val name: String,
  override val description: String,
  override val image: String,
  override val keywords: List<String>
) : GameAspect()