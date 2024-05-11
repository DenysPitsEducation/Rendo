package com.rendo.core.favorites.domain.repository

import com.rendo.core.product.ProductDomainModel
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun getFavoriteProducts(): List<ProductDomainModel>
    fun getFavoriteProductsFlow(): Flow<List<ProductDomainModel>>
    fun addFavorite(product: ProductDomainModel)
    fun removeFavorite(id: String)
}