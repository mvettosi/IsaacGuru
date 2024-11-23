package com.isaacguru.domain.transformation.repository

import com.isaacguru.domain.transformation.model.Transformation

interface TransformationRepository {
  suspend fun getTransformations(): List<Transformation>
}