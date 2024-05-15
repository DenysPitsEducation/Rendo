package com.rendo.feature.favorites.domain.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.rendo.core.favorites.domain.usecase.GetFavoritesFlowUseCase
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

internal class FavoritesBootstrapper(
    private val getFavoritesFlowUseCase: GetFavoritesFlowUseCase,
    private vararg val actions: FavoritesAction,
) : CoroutineBootstrapper<FavoritesAction>() {

    override fun invoke() {
        actions.forEach(::dispatch)
        collectFavorites()
        collectAuthorizationChange()
    }

    private fun collectFavorites() {
        scope.launch {
            getFavoritesFlowUseCase.invoke().collect {
                dispatch(FavoritesAction.FavoritesListUpdated(it))
            }
        }
    }

    private fun collectAuthorizationChange() {
        scope.launch {
            Firebase.auth.authStateChanged
                .map { user -> user != null && !user.isAnonymous }
                .collect { isAuthorized ->
                    dispatch(FavoritesAction.AuthorizationStateUpdated(isAuthorized = isAuthorized))
                }
        }
    }
}
