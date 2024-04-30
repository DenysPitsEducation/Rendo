package com.rendo.feature.product.details.domain.mvi

sealed class ProductDetailsIntent {
    data class SearchInputChanged(val input: String) : ProductDetailsIntent()
}
