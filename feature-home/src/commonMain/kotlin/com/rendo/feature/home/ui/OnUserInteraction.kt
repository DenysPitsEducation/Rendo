package com.rendo.feature.home.ui

import com.rendo.feature.home.domain.mvi.HomeIntent

internal typealias OnUserInteraction = (HomeIntent) -> Unit
