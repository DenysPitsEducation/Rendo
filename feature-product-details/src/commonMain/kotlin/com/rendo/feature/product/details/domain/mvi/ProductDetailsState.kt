package com.rendo.feature.product.details.domain.mvi

import com.rendo.feature.product.details.domain.model.ProductDetailsDomainModel

internal data class ProductDetailsState(
    val product: ProductDetailsDomainModel?,
)
