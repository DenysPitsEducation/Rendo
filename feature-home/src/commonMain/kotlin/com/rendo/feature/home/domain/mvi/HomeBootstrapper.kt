package com.rendo.feature.home.domain.mvi

import androidx.paging.cachedIn
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.rendo.core.favorites.domain.usecase.GetFavoritesFlowUseCase
import com.rendo.core.utils.DiffFlow
import com.rendo.core.utils.toDiffFlow
import com.rendo.feature.home.domain.usecase.GetProductsUseCase
import kotlinx.coroutines.launch

internal class HomeBootstrapper(
    private val getProductsUseCase: GetProductsUseCase,
    private val getFavoritesFlowUseCase: GetFavoritesFlowUseCase,
    private vararg val actions: HomeAction,
) : CoroutineBootstrapper<HomeAction>() {

    override fun invoke() {
        actions.forEach(::dispatch)
        collectProducts()
        collectFavorites()
    }

    private fun collectProducts() {
        scope.launch {
            getProductsUseCase.invoke().cachedIn(scope).collect { pagingData ->
                dispatch(HomeAction.ProductListUpdated(pagingData))
            }
        }
    }

    private fun collectFavorites() {
        scope.launch {
            getFavoritesFlowUseCase.invoke().toDiffFlow(areItemsTheSame = { oldItem, newItem ->
                oldItem.id == newItem.id
            }).collect { listUpdate ->
                listUpdate.itemUpdates.forEach { itemUpdate ->
                    when(itemUpdate) {
                        is DiffFlow.ItemUpdate.Added -> dispatch(HomeAction.FavoriteStateChanged(itemUpdate.newItem.id, isInFavorites = true))
                        is DiffFlow.ItemUpdate.Removed -> dispatch(HomeAction.FavoriteStateChanged(itemUpdate.oldItem.id, isInFavorites = false))
                        is DiffFlow.ItemUpdate.Changed -> { /* ignore */ }
                    }
                }
            }
        }
    }
}
