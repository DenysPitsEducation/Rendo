package com.rendo.feature.home.domain.mvi

import app.cash.paging.PagingData
import com.rendo.core.product.ProductDomainModel

internal sealed class HomeMessage {
    data class SearchInputChanged(val input: String) : HomeMessage()
    data class ProductListUpdated(val products: PagingData<ProductDomainModel>) : HomeMessage()
}
