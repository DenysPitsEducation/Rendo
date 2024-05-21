package com.rendo.feature.home.domain.mvi

import com.rendo.core.product.ProductDomainModel
import com.rendo.feature.home.domain.model.PaginationState

internal sealed class HomeMessage {
    data class SearchInputChanged(val input: String) : HomeMessage()
    data class ProductListUpdated(val products: List<ProductDomainModel>) : HomeMessage()
    data class ProductUpdated(val product: ProductDomainModel) : HomeMessage()
    data class PaginationStateUpdated(val state: PaginationState) : HomeMessage()
    data class ProductsAdded(val products: List<ProductDomainModel>) : HomeMessage()
}
