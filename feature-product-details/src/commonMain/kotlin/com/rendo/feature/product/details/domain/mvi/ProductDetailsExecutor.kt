package com.rendo.feature.product.details.domain.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.rendo.feature.product.details.domain.usecase.GetProductDetailsUseCase

internal class ProductDetailsExecutor(
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
) : CoroutineExecutor<ProductDetailsIntent, ProductDetailsAction, ProductDetailsState, ProductDetailsMessage, ProductDetailsLabel>() {
    override fun executeAction(action: ProductDetailsAction, getState: () -> ProductDetailsState) = when (action) {
        is ProductDetailsAction.Init -> {
            val product = getProductDetailsUseCase.invoke(action.payload.id)
            dispatch(ProductDetailsMessage.ProductUpdated(product))
        }
    }

    override fun executeIntent(intent: ProductDetailsIntent, getState: () -> ProductDetailsState) = when (intent) {
        is ProductDetailsIntent.SearchInputChanged -> {
            // TODO Pits:  
        }
    }
}