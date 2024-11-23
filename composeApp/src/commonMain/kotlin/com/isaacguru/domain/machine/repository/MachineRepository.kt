package com.isaacguru.domain.machine.repository

import com.isaacguru.domain.machine.model.Machine

interface MachineRepository {
  suspend fun getMachines(): List<Machine>
}