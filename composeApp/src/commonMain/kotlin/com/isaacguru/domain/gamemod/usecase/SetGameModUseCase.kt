package com.isaacguru.domain.gamemod.usecase

import com.isaacguru.domain.gamemod.model.GameMod

class SetGameModUseCase() {
  suspend operator fun invoke(gameMod: GameMod) = runCatching {}
}
