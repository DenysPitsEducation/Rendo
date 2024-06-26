package com.rendo.feature.product.details.domain.mvi

internal sealed class ProductDetailsAction {
    data class Init(val payload: ProductDetailsPayload) : ProductDetailsAction()
    data class FavoriteStateChanged(val isInFavorites: Boolean) : ProductDetailsAction()
}
