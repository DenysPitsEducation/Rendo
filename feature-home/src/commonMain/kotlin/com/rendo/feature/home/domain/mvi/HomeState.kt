package com.rendo.feature.home.domain.mvi

import com.rendo.core.product.ProductDomainModel

internal data class HomeState(
    val searchInput: String,
    val products: List<ProductDomainModel>,
) {
    val visibleProducts: List<ProductDomainModel> = products.filter { it.name.contains(searchInput, ignoreCase = true) }
}
