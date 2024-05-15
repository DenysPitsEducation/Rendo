package com.rendo.feature.rents.domain.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.launch

internal class RentsBootstrapper(
    private vararg val actions: RentsAction,
) : CoroutineBootstrapper<RentsAction>() {

    override fun invoke() {
        actions.forEach(::dispatch)
        collectAuthorizationChange()
    }

    private fun collectAuthorizationChange() {
        scope.launch {
            Firebase.auth.authStateChanged.collect { user ->
                val isAuthorized = user != null && !user.isAnonymous
                dispatch(RentsAction.AuthorizationStateUpdated(isAuthorized = isAuthorized))
            }
        }
    }
}
