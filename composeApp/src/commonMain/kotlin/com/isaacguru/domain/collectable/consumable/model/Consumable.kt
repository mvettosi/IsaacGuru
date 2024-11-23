package com.isaacguru.domain.collectable.consumable.model

import com.isaacguru.domain.collectable.Collectible

data class Consumable(
  override val id: String,
  override val name: String,
  override val description: String,
  override val image: String,
  override val keywords: List<String>,
  override val quote: String?,
  override val unlockMethod: String?,
  val typeDescription: String,
) : Collectible()