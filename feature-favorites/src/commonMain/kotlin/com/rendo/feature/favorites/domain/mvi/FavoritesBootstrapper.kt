package com.rendo.feature.favorites.domain.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.rendo.core.favorites.domain.usecase.GetFavoritesFlowUseCase
import kotlinx.coroutines.launch

internal class FavoritesBootstrapper(
    private val getFavoritesFlowUseCase: GetFavoritesFlowUseCase,
    private vararg val actions: FavoritesAction,
) : CoroutineBootstrapper<FavoritesAction>() {

    override fun invoke() {
        actions.forEach(::dispatch)
        collectFavorites()
    }

    private fun collectFavorites() {
        scope.launch {
            getFavoritesFlowUseCase.invoke().collect {
                dispatch(FavoritesAction.FavoritesListUpdated(it))
            }
        }
    }
}
