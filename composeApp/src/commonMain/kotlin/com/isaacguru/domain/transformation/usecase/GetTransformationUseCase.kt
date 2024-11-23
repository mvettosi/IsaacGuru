package com.isaacguru.domain.transformation.usecase

import com.isaacguru.domain.transformation.repository.TransformationRepository

class GetTransformationUseCase(private val transformationRepository: TransformationRepository) {
  suspend operator fun invoke() = runCatching { transformationRepository.getTransformations() }
}
