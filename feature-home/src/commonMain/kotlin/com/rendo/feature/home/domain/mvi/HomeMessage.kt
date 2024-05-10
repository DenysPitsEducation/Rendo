package com.rendo.feature.home.domain.mvi

import com.rendo.core.product.ProductDomainModel

internal sealed class HomeMessage {
    data class SearchInputChanged(val input: String) : HomeMessage()
    data class ProductListUpdated(val products: List<ProductDomainModel>) : HomeMessage()
    data class ProductUpdated(val product: ProductDomainModel) : HomeMessage()
}
