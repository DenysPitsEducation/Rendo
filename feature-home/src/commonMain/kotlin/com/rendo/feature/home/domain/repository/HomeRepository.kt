package com.rendo.feature.home.domain.repository

import app.cash.paging.PagingData
import com.rendo.core.product.ProductDomainModel
import kotlinx.coroutines.flow.Flow

internal interface HomeRepository {
    suspend fun getProducts(): Flow<PagingData<ProductDomainModel>>
}