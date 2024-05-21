package com.rendo.feature.home.domain.mvi

import app.cash.paging.PagingData
import app.cash.paging.filter
import com.rendo.core.product.ProductDomainModel

internal data class HomeState(
    val searchInput: String,
    val products: PagingData<ProductDomainModel>,
) {
    val visibleProducts: PagingData<ProductDomainModel> = products.filter { it.name.contains(searchInput, ignoreCase = true) }
}
