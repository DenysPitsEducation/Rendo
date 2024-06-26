package com.rendo.feature.rents.domain.usecase

import com.rendo.feature.rents.domain.model.RentDomainModel
import com.rendo.feature.rents.domain.repository.RentsRepository

internal class GetRentsUseCase(
    private val rentsRepository: RentsRepository,
) {
    suspend fun invoke(): Result<List<RentDomainModel>> {
        return rentsRepository.getRents()
    }
}