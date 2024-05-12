package com.rendo.feature.advertisements.domain.repository

import com.rendo.feature.advertisements.domain.model.AdvertisementDomainModel

internal interface AdvertisementsRepository {
    suspend fun getAdvertisements(): List<AdvertisementDomainModel>
    suspend fun deleteAdvertisement(id: String): Result<Unit>
}