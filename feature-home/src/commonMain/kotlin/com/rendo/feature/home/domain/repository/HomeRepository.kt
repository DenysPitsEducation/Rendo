package com.rendo.feature.home.domain.repository

import com.rendo.feature.home.domain.model.ProductDomainModel

interface HomeRepository {
    fun getProducts(): List<ProductDomainModel>
}