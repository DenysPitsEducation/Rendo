package com.rendo.feature.advertisements.domain.mvi

internal sealed class AdvertisementsAction {
    data object Init : AdvertisementsAction()
}
