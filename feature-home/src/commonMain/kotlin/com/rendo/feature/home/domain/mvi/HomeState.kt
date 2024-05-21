package com.rendo.feature.home.domain.mvi

import com.rendo.core.product.ProductDomainModel
import com.rendo.feature.home.domain.model.PaginationState

internal data class HomeState(
    val searchInput: String,
    val products: List<ProductDomainModel>,
    val paginationState: PaginationState,
) {
    val visibleProducts: List<ProductDomainModel> = products.filter { it.name.contains(searchInput, ignoreCase = true) }
}
