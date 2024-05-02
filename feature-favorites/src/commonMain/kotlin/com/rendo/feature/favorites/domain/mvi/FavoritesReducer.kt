package com.rendo.feature.favorites.domain.mvi

import com.arkivanov.mvikotlin.core.store.Reducer

internal class FavoritesReducer : Reducer<FavoritesState, FavoritesMessage> {
    override fun FavoritesState.reduce(
        msg: FavoritesMessage,
    ): FavoritesState = when (msg) {
        is FavoritesMessage.FavoritesListUpdated -> copy(products = msg.products)
        is FavoritesMessage.ProductUpdated -> {
            val productsUpdated = products.map { product ->
                if (msg.product.id == product.id) {
                    msg.product
                } else {
                    product
                }
            }
            copy(products = productsUpdated)
        }
    }
}