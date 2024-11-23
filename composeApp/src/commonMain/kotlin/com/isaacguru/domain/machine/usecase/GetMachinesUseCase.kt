package com.isaacguru.domain.machine.usecase

import com.isaacguru.domain.machine.repository.MachineRepository

class GetMachinesUseCase(private val machineRepository: MachineRepository) {
  suspend operator fun invoke() = runCatching { machineRepository.getMachines() }
}
