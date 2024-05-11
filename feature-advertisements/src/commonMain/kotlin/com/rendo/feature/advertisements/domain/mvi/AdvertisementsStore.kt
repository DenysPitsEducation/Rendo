package com.rendo.feature.advertisements.domain.mvi

import com.arkivanov.mvikotlin.core.store.Store

internal typealias AdvertisementsStore = Store<AdvertisementsIntent, AdvertisementsState, AdvertisementsLabel>
