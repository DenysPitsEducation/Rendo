package com.rendo.feature.advertisements.ui

import com.rendo.feature.advertisements.domain.mvi.AdvertisementsIntent

internal typealias OnUserInteraction = (AdvertisementsIntent) -> Unit
