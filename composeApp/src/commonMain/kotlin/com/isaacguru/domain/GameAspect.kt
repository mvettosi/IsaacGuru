package com.isaacguru.domain

abstract class GameAspect {
  abstract val id: String
  abstract val name: String
  abstract val description: String
  abstract val image: String
  abstract val keywords: List<String>
}
