package com.rendo.feature.home.domain.mvi

import com.rendo.core.product.ProductUiModel

internal sealed class HomeIntent {
    data class SearchInputChanged(val input: String) : HomeIntent()
    data class ProductClicked(val id: String) : HomeIntent()
    data class FavoriteButtonClicked(val model: ProductUiModel) : HomeIntent()
}
