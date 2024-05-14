package com.rendo.feature.rents.domain.usecase

import com.rendo.feature.rents.domain.model.RentDomainModel
import com.rendo.feature.rents.domain.repository.RentsRepository

internal class AcceptRentUseCase(
    private val rentsRepository: RentsRepository,
) {
    suspend fun invoke(rent: RentDomainModel): Result<Unit> {
        return rentsRepository.acceptRent(rent)
    }
}