package com.rendo.feature.favorites.domain.mvi

import com.rendo.core.product.ProductDomainModel

data class FavoritesState(
    val products: List<ProductDomainModel>,
)
