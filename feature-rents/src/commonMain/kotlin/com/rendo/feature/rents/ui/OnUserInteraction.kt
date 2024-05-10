package com.rendo.feature.rents.ui

import com.rendo.feature.rents.domain.mvi.RentsIntent

internal typealias OnUserInteraction = (RentsIntent) -> Unit
