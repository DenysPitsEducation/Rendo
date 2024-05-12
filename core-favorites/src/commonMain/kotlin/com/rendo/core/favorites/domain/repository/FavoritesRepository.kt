package com.rendo.core.favorites.domain.repository

import com.rendo.core.product.ProductDomainModel
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    suspend fun refreshFavoriteProducts()
    fun getFavoriteProducts(): List<ProductDomainModel>
    fun getFavoriteProductsFlow(): Flow<List<ProductDomainModel>>
    suspend fun addFavorite(product: ProductDomainModel)
    suspend fun removeFavorite(id: String)
}