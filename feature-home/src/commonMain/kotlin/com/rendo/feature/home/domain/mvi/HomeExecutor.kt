package com.rendo.feature.home.domain.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.rendo.core.favorites.domain.usecase.ChangeFavoriteStateUseCase
import com.rendo.core.utils.indexOfFirstOrNull
import com.rendo.feature.home.domain.model.PaginationState
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
            val products = getProductsUseCase.invoke(lastVisibleProductId = null)
            dispatch(HomeMessage.ProductListUpdated(products))
            dispatch(HomeMessage.PaginationStateUpdated(PaginationState.IDLE))
        }
    }

    override fun executeIntent(intent: HomeIntent) = when (intent) {
        is HomeIntent.SearchInputChanged -> onSearchInputChanged(intent)
        is HomeIntent.ProductClicked -> onProductClicked(intent)
        is HomeIntent.FavoriteButtonClicked -> onFavoriteButtonClicked(intent)
        is HomeIntent.ProductWatched -> onProductWatched(intent)
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

    private fun onProductWatched(intent: HomeIntent.ProductWatched) {
        val state = state()
        val position = state.products.indexOfFirstOrNull { it.id == intent.id } ?: return
        val itemsTillTheEnd = state.products.size - position
        if (itemsTillTheEnd < ITEMS_TILL_THE_END_TO_REQUEST_PAGINATION && state.paginationState == PaginationState.IDLE) {
            requestPagination()
        }
    }

    private fun requestPagination() = scope.launch {
        dispatch(HomeMessage.PaginationStateUpdated(state = PaginationState.LOADING))

        val lastVisibleProductId = state().products.lastOrNull()?.id
        val products = getProductsUseCase.invoke(lastVisibleProductId)

        if (products.isEmpty()) {
            dispatch(HomeMessage.PaginationStateUpdated(PaginationState.END_OF_PAGINATION))
        } else {
            dispatch(HomeMessage.PaginationStateUpdated(PaginationState.IDLE))
            dispatch(HomeMessage.ProductsAdded(products))
        }
    }

    companion object {
        const val ITEMS_TILL_THE_END_TO_REQUEST_PAGINATION = 6
    }
}