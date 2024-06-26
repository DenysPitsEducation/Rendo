package com.rendo.feature.favorites.domain.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.rendo.core.favorites.domain.usecase.RefreshFavoriteProductsUseCase
import com.rendo.core.favorites.domain.usecase.RemoveFavoriteUseCase
import kotlinx.coroutines.launch

internal class FavoritesExecutor(
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
    private val refreshFavoriteProductsUseCase: RefreshFavoriteProductsUseCase,
) : CoroutineExecutor<FavoritesIntent, FavoritesAction, FavoritesState, FavoritesMessage, FavoritesLabel>() {

    override fun executeAction(action: FavoritesAction) = when (action) {
        is FavoritesAction.FavoritesListUpdated -> dispatch(FavoritesMessage.FavoritesListUpdated(action.products))
        is FavoritesAction.AuthorizationStateUpdated -> onAuthorizationStateUpdated()
    }

    private fun onAuthorizationStateUpdated() {
        scope.launch {
            refreshFavoriteProductsUseCase.invoke()
        }
    }

    override fun executeIntent(intent: FavoritesIntent) = when (intent) {
        is FavoritesIntent.ProductClicked -> onProductClicked(intent)
        is FavoritesIntent.FavoriteStateRemoved -> onFavoriteStateChanged(intent)
    }

    private fun onProductClicked(intent: FavoritesIntent.ProductClicked) {
        publish(FavoritesLabel.OpenProductDetails(id = intent.id))
    }

    private fun onFavoriteStateChanged(intent: FavoritesIntent.FavoriteStateRemoved) {
        scope.launch {
            removeFavoriteUseCase.invoke(id = intent.id)
        }
    }
}