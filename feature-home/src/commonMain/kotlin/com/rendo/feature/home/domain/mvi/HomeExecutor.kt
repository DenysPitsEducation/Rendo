package com.rendo.feature.home.domain.mvi

import app.cash.paging.map
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.rendo.core.favorites.domain.usecase.ChangeFavoriteStateUseCase
import kotlinx.coroutines.launch

internal class HomeExecutor(
    private val changeFavoriteStateUseCase: ChangeFavoriteStateUseCase,
) : CoroutineExecutor<HomeIntent, HomeAction, HomeState, HomeMessage, HomeLabel>() {

    override fun executeAction(action: HomeAction) = when (action) {
        is HomeAction.ProductListUpdated -> onProductListUpdated(action)
        is HomeAction.FavoriteStateChanged -> onFavoriteStateChanged(action)
    }

    private fun onProductListUpdated(action: HomeAction.ProductListUpdated) {
        dispatch(HomeMessage.ProductListUpdated(action.products))
    }

    override fun executeIntent(intent: HomeIntent) = when (intent) {
        is HomeIntent.SearchInputChanged -> onSearchInputChanged(intent)
        is HomeIntent.ProductClicked -> onProductClicked(intent)
        is HomeIntent.FavoriteButtonClicked -> onFavoriteButtonClicked(intent)
    }

    private fun onSearchInputChanged(intent: HomeIntent.SearchInputChanged) {
        dispatch(HomeMessage.SearchInputChanged(intent.input))
    }

    private fun onProductClicked(intent: HomeIntent.ProductClicked) {
        publish(HomeLabel.OpenProductDetails(id = intent.id))
    }

    private fun onFavoriteStateChanged(action: HomeAction.FavoriteStateChanged) {
        val productsUpdated = state().products.map { product ->
            if (product.id == action.id) {
                product.copy(isInFavorites = action.isInFavorites)
            } else {
                product
            }
        }
        dispatch(HomeMessage.ProductListUpdated(productsUpdated))
    }

    private fun onFavoriteButtonClicked(intent: HomeIntent.FavoriteButtonClicked) {
        scope.launch {
            val productDomainModel = intent.model.payload ?: return@launch
            changeFavoriteStateUseCase.invoke(productDomainModel)
        }
    }
}