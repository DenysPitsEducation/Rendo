package com.rendo.feature.home.domain.mvi

import com.rendo.feature.home.domain.model.ProductDomainModel

data class HomeState(
    val searchInput: String,
    val products: List<ProductDomainModel>,
)
