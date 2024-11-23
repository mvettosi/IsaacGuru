package com.isaacguru

interface Platform {
  val name: String
}

expect fun getPlatform(): Platform
