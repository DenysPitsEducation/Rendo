package com.rendo.feature.create.rent.domain.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper

internal class CreateRentBootstrapper(
    private vararg val actions: CreateRentAction,
) : CoroutineBootstrapper<CreateRentAction>() {

    override fun invoke() {
        actions.forEach(::dispatch)
    }
}
