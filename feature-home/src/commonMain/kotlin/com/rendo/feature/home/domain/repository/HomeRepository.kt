package com.rendo.feature.home.domain.repository

import com.rendo.core.product.ProductDomainModel

internal interface HomeRepository {
    fun getProducts(): List<ProductDomainModel>
}