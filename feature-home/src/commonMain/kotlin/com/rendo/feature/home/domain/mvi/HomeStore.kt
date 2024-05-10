package com.rendo.feature.home.domain.mvi

import com.arkivanov.mvikotlin.core.store.Store

internal typealias HomeStore = Store<HomeIntent, HomeState, HomeLabel>
