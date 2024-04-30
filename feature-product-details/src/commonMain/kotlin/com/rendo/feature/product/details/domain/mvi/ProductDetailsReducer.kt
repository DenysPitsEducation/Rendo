package com.rendo.feature.product.details.domain.mvi

import com.arkivanov.mvikotlin.core.store.Reducer

internal class ProductDetailsReducer : Reducer<ProductDetailsState, ProductDetailsMessage> {
    override fun ProductDetailsState.reduce(
        msg: ProductDetailsMessage,
    ): ProductDetailsState = when (msg) {
        is ProductDetailsMessage.ProductUpdated -> copy(product = msg.product)
    }
}