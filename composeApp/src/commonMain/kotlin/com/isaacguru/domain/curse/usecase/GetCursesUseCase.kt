package com.isaacguru.domain.curse.usecase

import com.isaacguru.domain.curse.repository.CurseRepository

class GetCursesUseCase(private val curseRepository: CurseRepository) {
  suspend operator fun invoke() = runCatching {
     curseRepository.getCurses()
  }
}