package com.isaacguru.domain.device.usecase

class IsNetworkAvailableUseCase() {
  suspend operator fun invoke(): Result<Boolean> = runCatching {
    false // TODO implement
  }
}
