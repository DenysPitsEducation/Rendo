package com.rendo.feature.advertisements.domain.repository

import com.rendo.feature.advertisements.domain.model.AdvertisementDomainModel

internal interface AdvertisementsRepository {
    fun getAdvertisements(): List<AdvertisementDomainModel>
}