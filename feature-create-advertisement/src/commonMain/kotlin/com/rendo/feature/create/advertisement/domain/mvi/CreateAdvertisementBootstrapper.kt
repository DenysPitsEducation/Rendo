package com.rendo.feature.create.advertisement.domain.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper

internal class CreateAdvertisementBootstrapper(
    private vararg val actions: CreateAdvertisementAction,
) : CoroutineBootstrapper<CreateAdvertisementAction>() {

    override fun invoke() {
        actions.forEach(::dispatch)
    }
}
