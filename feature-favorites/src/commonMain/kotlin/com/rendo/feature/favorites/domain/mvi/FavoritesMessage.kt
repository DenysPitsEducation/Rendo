package com.rendo.feature.favorites.domain.mvi

import com.rendo.core.product.ProductDomainModel

internal sealed class FavoritesMessage {
    data class FavoritesListUpdated(val products: List<ProductDomainModel>) : FavoritesMessage()
    data class ProductUpdated(val product: ProductDomainModel) : FavoritesMessage()
}
