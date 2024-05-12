package com.rendo.feature.home.domain.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.rendo.core.favorites.domain.usecase.ChangeFavoriteStateUseCase
import com.rendo.feature.home.domain.usecase.GetProductsUseCase
import kotlinx.coroutines.launch

internal class HomeExecutor(
    private val getProductsUseCase: GetProductsUseCase,
    private val changeFavoriteStateUseCase: ChangeFavoriteStateUseCase,
) : CoroutineExecutor<HomeIntent, HomeAction, HomeState, HomeMessage, HomeLabel>() {

    override fun executeAction(action: HomeAction) = when (action) {
        is HomeAction.Init -> onInit()
        is HomeAction.FavoriteStateChanged -> onFavoriteStateChanged(action)
    }

    private fun onInit() {
        scope.launch {
            val products = getProductsUseCase.invoke()
            dispatch(HomeMessage.ProductListUpdated(products))
        }
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
        val product = state().products.firstOrNull { it.id == action.id } ?: return
        val productUpdated = product.copy(isInFavorites = action.isInFavorites)
        dispatch(HomeMessage.ProductUpdated(productUpdated))
    }

    private fun onFavoriteButtonClicked(intent: HomeIntent.FavoriteButtonClicked) {
        scope.launch {
            val product = state().products.firstOrNull { it.id == intent.id } ?: return@launch
            changeFavoriteStateUseCase.invoke(product)
        }
    }
}