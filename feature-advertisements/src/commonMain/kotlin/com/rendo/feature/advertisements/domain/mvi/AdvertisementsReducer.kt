package com.rendo.feature.advertisements.domain.mvi

import com.arkivanov.mvikotlin.core.store.Reducer

internal class AdvertisementsReducer : Reducer<AdvertisementsState, AdvertisementsMessage> {
    override fun AdvertisementsState.reduce(
        msg: AdvertisementsMessage,
    ): AdvertisementsState = when (msg) {
        is AdvertisementsMessage.AdvertisementsUpdated -> copy(advertisements = msg.advertisements)
    }
}