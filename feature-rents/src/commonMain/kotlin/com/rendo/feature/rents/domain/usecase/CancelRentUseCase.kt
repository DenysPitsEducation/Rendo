package com.rendo.feature.rents.domain.usecase

import com.rendo.feature.rents.domain.model.RentDomainModel
import com.rendo.feature.rents.domain.repository.RentsRepository

internal class CancelRentUseCase(
    private val rentsRepository: RentsRepository,
) {
    suspend fun invoke(rent: RentDomainModel): Result<Unit> {
        return rentsRepository.cancelRent(rent)
    }
}