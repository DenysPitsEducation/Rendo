package com.rendo.feature.advertisements.domain.usecase

import com.rendo.feature.advertisements.domain.model.AdvertisementDomainModel
import com.rendo.feature.advertisements.domain.repository.AdvertisementsRepository

internal class GetAdvertisementsUseCase(
    private val rentsRepository: AdvertisementsRepository,
) {
    suspend fun invoke(): List<AdvertisementDomainModel> {
        return rentsRepository.getAdvertisements()
    }
}