package com.rendo.feature.product.details.domain.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.rendo.core.favorites.domain.usecase.GetFavoritesFlowUseCase
import com.rendo.core.utils.DiffFlow
import com.rendo.core.utils.toDiffFlow
import kotlinx.coroutines.launch

internal class ProductDetailsBootstrapper(
    private val productId: Long,
    private val getFavoritesFlowUseCase: GetFavoritesFlowUseCase,
    private vararg val actions: ProductDetailsAction,
) : CoroutineBootstrapper<ProductDetailsAction>() {

    override fun invoke() {
        actions.forEach(::dispatch)
        collectFavorites()
    }

    private fun collectFavorites() {
        scope.launch {
            getFavoritesFlowUseCase.invoke().collect { favorites ->
                if (favorites.any { it.id == productId }) {
                    dispatch(ProductDetailsAction.FavoriteStateChanged(isInFavorites = true))
                } else {
                    dispatch(ProductDetailsAction.FavoriteStateChanged(isInFavorites = false))
                }
            }
        }
    }
}
