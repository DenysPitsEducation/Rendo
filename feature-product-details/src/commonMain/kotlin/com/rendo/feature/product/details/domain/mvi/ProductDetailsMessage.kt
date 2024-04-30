package com.rendo.feature.product.details.domain.mvi

import com.rendo.feature.product.details.domain.model.ProductDetailsDomainModel

sealed class ProductDetailsMessage {
    data class ProductUpdated(val product: ProductDetailsDomainModel) : ProductDetailsMessage()
}
