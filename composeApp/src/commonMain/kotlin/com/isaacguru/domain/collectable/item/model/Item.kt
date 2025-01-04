package com.isaacguru.domain.collectable.item.model

import com.isaacguru.domain.collectable.Collectible

sealed class Item : Collectible() {
  abstract val costume: String
  abstract val itemPools: List<String>
  abstract val quality: Int
  abstract val transformations: List<String>

  data class Active(
      override val id: String,
      override val name: String,
      override val description: String,
      override val image: String,
      override val keywords: List<String>,
      override val quote: String?,
      override val unlockMethod: String,
      override val costume: String,
      override val itemPools: List<String>,
      override val quality: Int,
      override val transformations: List<String>,
      val maxCharges: Int,
      val chargeType: ChargeType = ChargeType.DEFAULT,
      val cooldown: Int = 0,
  ) : Item() {
    enum class ChargeType {
      DEFAULT,
      TIMED,
      SPECIAL
    }
  }

  data class Passive(
      override val id: String,
      override val name: String,
      override val description: String,
      override val image: String,
      override val keywords: List<String>,
      override val quote: String?,
      override val unlockMethod: String,
      override val costume: String,
      override val itemPools: List<String>,
      override val quality: Int,
      override val transformations: List<String>
  ) : Item()
}
