package com.rendo.feature.rents.domain.mvi

import com.arkivanov.mvikotlin.core.store.Store

internal typealias RentsStore = Store<RentsIntent, RentsState, RentsLabel>
