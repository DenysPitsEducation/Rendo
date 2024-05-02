package com.rendo.feature.home.domain.repository

import com.rendo.core.product.ProductDomainModel

interface HomeRepository {
    fun getProducts(): List<ProductDomainModel>
}