package com.rendo.feature.advertisements.domain.mvi

internal sealed class AdvertisementsIntent {
    data class AdvertisementClicked(val id: String) : AdvertisementsIntent()
    data class DeleteButtonClicked(val id: String) : AdvertisementsIntent()
}
