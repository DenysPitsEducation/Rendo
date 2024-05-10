package com.rendo.core.favorites.data.repository

import com.rendo.core.favorites.data.source.FavoritesLocalDataSource
import com.rendo.core.favorites.domain.repository.FavoritesRepository
import com.rendo.core.product.ProductDomainModel
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
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

    override fun removeFavorite(id: Long) {
        val favorites = favoritesLocalDataSource.get()
        val favoritesUpdated = favorites.filter { it.id != id }
        favoritesLocalDataSource.set(favoritesUpdated)
    }

    suspend fun getInitial() {
        val user = hashMapOf(
            "first" to "Ada",
            "last" to "Lovelace",
            "born" to 1815,
            "isMale" to true,
        )
        Firebase.firestore.collection("users").add(user)
    }
}