package com.rendo.feature.home.domain.mvi

import com.rendo.feature.home.domain.model.ProductDomainModel

sealed class HomeMessage {
    data class SearchInputChanged(val input: String) : HomeMessage()
    data class ProductListUpdated(val products: List<ProductDomainModel>) : HomeMessage()
    data class ProductUpdated(val product: ProductDomainModel) : HomeMessage()
}
