package com.rendo.feature.home.domain.mvi

import app.cash.paging.PagingData
import com.rendo.core.product.ProductDomainModel

internal sealed class HomeAction {
    data class ProductListUpdated(val products: PagingData<ProductDomainModel>) : HomeAction()
    data class FavoriteStateChanged(val id: String, val isInFavorites: Boolean) : HomeAction()
}
