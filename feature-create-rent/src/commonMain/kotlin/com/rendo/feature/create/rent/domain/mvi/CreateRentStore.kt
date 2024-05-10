package com.rendo.feature.create.rent.domain.mvi

import com.arkivanov.mvikotlin.core.store.Store

internal typealias CreateRentStore = Store<CreateRentIntent, CreateRentState, CreateRentLabel>
