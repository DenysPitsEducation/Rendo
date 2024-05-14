package com.rendo.feature.rents.domain.usecase

import com.rendo.feature.rents.domain.repository.RentsRepository

internal class DeleteRentUseCase(
    private val rentsRepository: RentsRepository,
) {
    suspend fun invoke(rentId: String): Result<Unit> {
        return rentsRepository.deleteRent(rentId)
    }
}
