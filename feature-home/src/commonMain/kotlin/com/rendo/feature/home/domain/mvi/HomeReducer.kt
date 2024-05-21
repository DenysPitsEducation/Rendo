package com.rendo.feature.home.domain.mvi

import com.arkivanov.mvikotlin.core.store.Reducer

internal class HomeReducer : Reducer<HomeState, HomeMessage> {
    override fun HomeState.reduce(
        msg: HomeMessage,
    ): HomeState = when (msg) {
        is HomeMessage.SearchInputChanged -> copy(searchInput = msg.input)
        is HomeMessage.ProductListUpdated -> copy(products = msg.products)
        is HomeMessage.ProductUpdated -> {
            val productsUpdated = products.map { product ->
                if (msg.product.id == product.id) {
                    msg.product
                } else {
                    product
                }
            }
            copy(products = productsUpdated)
        }
        is HomeMessage.PaginationStateUpdated -> copy(paginationState = msg.state)
        is HomeMessage.ProductsAdded -> copy(products = products + msg.products)
    }
}