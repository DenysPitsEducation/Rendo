package com.rendo.feature.advertisements.domain.mvi

import com.rendo.feature.advertisements.domain.model.AdvertisementDomainModel

internal data class AdvertisementsState(
    val advertisements: List<AdvertisementDomainModel>,
)
