package com.isaacguru.domain.collectable

import com.isaacguru.domain.GameAspect

abstract class Collectible : GameAspect() {
  open val quote: String? = null
  abstract val unlockMethod: String?
}
