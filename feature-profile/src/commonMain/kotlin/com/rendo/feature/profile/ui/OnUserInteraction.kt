package com.rendo.feature.profile.ui

import com.rendo.feature.profile.domain.mvi.ProfileIntent

internal typealias OnUserInteraction = (ProfileIntent) -> Unit
