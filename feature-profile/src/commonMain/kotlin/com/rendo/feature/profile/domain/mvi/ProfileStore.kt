package com.rendo.feature.profile.domain.mvi

import com.arkivanov.mvikotlin.core.store.Store

internal typealias ProfileStore = Store<ProfileIntent, ProfileState, ProfileLabel>
