package com.rendo.feature.create.advertisement.domain.usecase

import com.rendo.feature.create.advertisement.domain.model.AdvertisementDomainModel
import com.rendo.feature.create.advertisement.domain.repository.CreateAdvertisementRepository

internal class CreateAdvertisementUseCase(
    private val repository: CreateAdvertisementRepository,
) {
    fun invoke(advertisement: AdvertisementDomainModel) {
        return repository.createRent()
    }
}