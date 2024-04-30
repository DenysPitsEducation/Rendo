package com.rendo.feature.product.details.domain.mvi

sealed class ProductDetailsAction {
    data class Init(val payload: ProductDetailsPayload) : ProductDetailsAction()
}
