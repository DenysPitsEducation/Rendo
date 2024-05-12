package com.rendo.feature.create.advertisement.ui

import com.rendo.feature.create.advertisement.domain.mvi.CreateAdvertisementIntent

internal typealias OnUserInteraction = (CreateAdvertisementIntent) -> Unit
