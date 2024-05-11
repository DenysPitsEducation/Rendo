package com.rendo.core.favorites.data.repository

import com.rendo.core.favorites.data.source.FavoritesLocalDataSource
import com.rendo.core.favorites.domain.repository.FavoritesRepository
import com.rendo.core.product.ProductDomainModel
import kotlinx.coroutines.flow.Flow

internal class FavoritesRepositoryImpl(
    private val favoritesLocalDataSource: FavoritesLocalDataSource
) : FavoritesRepository {

    override fun getFavoriteProducts(): List<ProductDomainModel> = favoritesLocalDataSource.get()

    override fun getFavoriteProductsFlow(): Flow<List<ProductDomainModel>> = favoritesLocalDataSource.getFlow()

    override fun addFavorite(product: ProductDomainModel) {
        val favorites = favoritesLocalDataSource.get()
        val favoritesUpdated = favorites + product
        favoritesLocalDataSource.set(favoritesUpdated)
    }

    override fun removeFavorite(id: String) {
        val favorites = favoritesLocalDataSource.get()
        val favoritesUpdated = favorites.filter { it.id != id }
        favoritesLocalDataSource.set(favoritesUpdated)
    }
}