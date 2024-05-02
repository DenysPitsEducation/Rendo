package com.rendo.feature.favorites.domain.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.rendo.core.favorites.domain.usecase.RemoveFavoriteUseCase

internal class FavoritesExecutor(
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
) : CoroutineExecutor<FavoritesIntent, FavoritesAction, FavoritesState, FavoritesMessage, FavoritesLabel>() {

    override fun executeAction(action: FavoritesAction) = when (action) {
        is FavoritesAction.FavoritesListUpdated -> dispatch(FavoritesMessage.FavoritesListUpdated(action.products))
    }

    override fun executeIntent(intent: FavoritesIntent) = when (intent) {
        is FavoritesIntent.ProductClicked -> onProductClicked(intent)
        is FavoritesIntent.FavoriteStateRemoved -> onFavoriteStateChanged(intent)
    }

    private fun onProductClicked(intent: FavoritesIntent.ProductClicked) {
        publish(FavoritesLabel.OpenProductDetails(id = intent.id))
    }

    private fun onFavoriteStateChanged(intent: FavoritesIntent.FavoriteStateRemoved) {
        removeFavoriteUseCase.invoke(id = intent.id)
    }
}