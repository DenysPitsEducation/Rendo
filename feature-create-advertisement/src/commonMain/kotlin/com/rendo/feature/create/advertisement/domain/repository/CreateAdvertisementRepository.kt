package com.rendo.feature.create.advertisement.domain.repository

import com.rendo.feature.create.advertisement.domain.model.AdvertisementDomainModel

internal interface CreateAdvertisementRepository {
    suspend fun createAdvertisement(advertisement: AdvertisementDomainModel): Result<Unit>
}