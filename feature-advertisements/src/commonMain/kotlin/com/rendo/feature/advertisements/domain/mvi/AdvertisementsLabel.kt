package com.rendo.feature.advertisements.domain.mvi

internal sealed class AdvertisementsLabel {
    data class OpenProductDetails(val id: String) : AdvertisementsLabel()
}
