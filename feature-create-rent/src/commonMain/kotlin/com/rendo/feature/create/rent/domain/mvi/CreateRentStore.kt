package com.rendo.feature.create.rent.domain.mvi

import com.arkivanov.mvikotlin.core.store.Store

typealias CreateRentStore = Store<CreateRentIntent, CreateRentState, CreateRentLabel>
