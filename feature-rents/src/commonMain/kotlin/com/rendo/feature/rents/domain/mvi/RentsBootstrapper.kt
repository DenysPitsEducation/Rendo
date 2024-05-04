package com.rendo.feature.rents.domain.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper

internal class RentsBootstrapper(
    private vararg val actions: RentsAction,
) : CoroutineBootstrapper<RentsAction>() {

    override fun invoke() {
        actions.forEach(::dispatch)
    }
}
