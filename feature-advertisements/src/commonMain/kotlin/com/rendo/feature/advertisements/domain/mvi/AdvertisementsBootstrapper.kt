package com.rendo.feature.advertisements.domain.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper

internal class AdvertisementsBootstrapper(
    private vararg val actions: AdvertisementsAction,
) : CoroutineBootstrapper<AdvertisementsAction>() {

    override fun invoke() {
        actions.forEach(::dispatch)
    }
}
