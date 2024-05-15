package com.rendo.feature.rents.domain.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.rendo.core.domain.listener.RentsUpdateListener
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

internal class RentsBootstrapper(
    private val rentsUpdateListener: RentsUpdateListener,
    private vararg val actions: RentsAction,
) : CoroutineBootstrapper<RentsAction>() {

    override fun invoke() {
        actions.forEach(::dispatch)
        collectAuthorizationChange()
        collectRentUpdates()
    }

    private fun collectAuthorizationChange() {
        scope.launch {
            Firebase.auth.authStateChanged
                .map { user -> user != null && !user.isAnonymous }
                .collect { isAuthorized ->
                    dispatch(RentsAction.AuthorizationStateUpdated(isAuthorized = isAuthorized))
                }
        }
    }

    private fun collectRentUpdates() {
        scope.launch {
            rentsUpdateListener.getUpdates().collect {
                dispatch(RentsAction.RentsUpdateRequested)
            }
        }
    }
}
