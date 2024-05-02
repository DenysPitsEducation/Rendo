package com.rendo.feature.home.domain.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.rendo.feature.home.domain.usecase.GetProductsUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

internal class HomeExecutor(
    private val getProductsUseCase: GetProductsUseCase,
) : CoroutineExecutor<HomeIntent, HomeAction, HomeState, HomeMessage, HomeLabel>() {

    override fun executeAction(action: HomeAction) = when (action) {
        HomeAction.Init -> {
            val products = getProductsUseCase.invoke()
            dispatch(HomeMessage.ProductListUpdated(products))
        }
    }

    override fun executeIntent(intent: HomeIntent) = when (intent) {
        is HomeIntent.SearchInputChanged -> onSearchInputChanged(intent)
        is HomeIntent.ProductClicked -> onProductClicked(intent)
        is HomeIntent.FavoriteStateChanged -> onFavoriteStateChanged(intent)
    }

    private fun onSearchInputChanged(intent: HomeIntent.SearchInputChanged) {
        dispatch(HomeMessage.SearchInputChanged(intent.input))
    }

    private fun onProductClicked(intent: HomeIntent.ProductClicked) {
        publish(HomeLabel.OpenProductDetails(id = intent.id))
    }

    private fun onFavoriteStateChanged(intent: HomeIntent.FavoriteStateChanged) {
        val product = state().products.firstOrNull { it.id == intent.id } ?: return
        val productUpdated = product.copy(isInFavorites = !product.isInFavorites)
        dispatch(HomeMessage.ProductUpdated(productUpdated))
    }
}