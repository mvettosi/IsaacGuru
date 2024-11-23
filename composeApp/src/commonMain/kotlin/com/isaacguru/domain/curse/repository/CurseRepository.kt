package com.isaacguru.domain.curse.repository

import com.isaacguru.domain.curse.model.Curse

interface CurseRepository {
  suspend fun getCurses(): List<Curse>
}
