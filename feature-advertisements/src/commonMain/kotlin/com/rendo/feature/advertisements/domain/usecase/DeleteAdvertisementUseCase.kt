package com.rendo.feature.advertisements.domain.usecase

import com.rendo.feature.advertisements.domain.repository.AdvertisementsRepository

internal class DeleteAdvertisementUseCase(
    private val rentsRepository: AdvertisementsRepository,
) {
    suspend fun invoke(id: String): Result<Unit> {
        return rentsRepository.deleteAdvertisement(id)
    }
}