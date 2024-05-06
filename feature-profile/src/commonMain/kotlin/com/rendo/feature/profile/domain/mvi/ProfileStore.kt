package com.rendo.feature.profile.domain.mvi

import com.arkivanov.mvikotlin.core.store.Store

typealias ProfileStore = Store<ProfileIntent, ProfileState, ProfileLabel>
