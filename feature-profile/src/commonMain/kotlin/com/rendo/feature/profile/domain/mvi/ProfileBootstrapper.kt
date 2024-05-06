package com.rendo.feature.profile.domain.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper

internal class ProfileBootstrapper(
    private vararg val actions: ProfileAction,
) : CoroutineBootstrapper<ProfileAction>() {

    override fun invoke() {
        actions.forEach(::dispatch)
    }
}
