package com.rendo.feature.create.advertisement.domain.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.launch

internal class CreateAdvertisementBootstrapper(
    private vararg val actions: CreateAdvertisementAction,
) : CoroutineBootstrapper<CreateAdvertisementAction>() {

    override fun invoke() {
        actions.forEach(::dispatch)
        collectAuthorization()
    }

    private fun collectAuthorization() {
        scope.launch {
            Firebase.auth.authStateChanged.collect { user ->
                val isAuthorized = user != null && !user.isAnonymous
                dispatch(CreateAdvertisementAction.AuthorizationStateUpdated(isAuthorized = isAuthorized))
            }
        }
    }
}
