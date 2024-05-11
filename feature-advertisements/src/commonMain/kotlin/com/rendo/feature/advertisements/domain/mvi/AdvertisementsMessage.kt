package com.rendo.feature.advertisements.domain.mvi

import com.rendo.feature.advertisements.domain.model.AdvertisementDomainModel

internal sealed class AdvertisementsMessage {
    data class AdvertisementsUpdated(val advertisements: List<AdvertisementDomainModel>) : AdvertisementsMessage()
}
