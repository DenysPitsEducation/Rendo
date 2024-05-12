package com.rendo.core.favorites.data.repository

import com.rendo.core.data.mapper.ProductDomainMapper
import com.rendo.core.data.model.ProductDataModel
import com.rendo.core.favorites.data.source.FavoritesLocalDataSource
import com.rendo.core.favorites.domain.repository.FavoritesRepository
import com.rendo.core.product.ProductDomainModel
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.FieldPath
import dev.gitlive.firebase.firestore.FieldValue
import dev.gitlive.firebase.firestore.firestore
import dev.gitlive.firebase.firestore.where
import kotlinx.coroutines.flow.Flow

internal class FavoritesRepositoryImpl(
    private val favoritesLocalDataSource: FavoritesLocalDataSource,
    private val productMapper: ProductDomainMapper,
) : FavoritesRepository {

    override suspend fun refreshFavoriteProducts() {
        val user = Firebase.auth.currentUser ?: return
        val usersCollection = Firebase.firestore.collection("users")
        val userDocument = usersCollection.document(user.uid)
        val favoriteIds = userDocument.get().get<List<String>?>("favorite_product_ids")

        if (favoriteIds.isNullOrEmpty()) {
            favoritesLocalDataSource.set(emptyList())
        } else {
            val productsCollection = Firebase.firestore.collection("products")
            val productDocuments = productsCollection.where {
                FieldPath.documentId inArray favoriteIds
            }.get().documents
            val productDomainModels = productDocuments.map { documentSnapshot ->
                val productDataModel = documentSnapshot.data(ProductDataModel.serializer())
                productMapper.mapToDomainModel(productDataModel, documentSnapshot.id).copy(isInFavorites = true)
            }
            favoritesLocalDataSource.set(productDomainModels)
        }
    }

    override fun getFavoriteProducts(): List<ProductDomainModel> = favoritesLocalDataSource.get()

    override fun getFavoriteProductsFlow(): Flow<List<ProductDomainModel>> = favoritesLocalDataSource.getFlow()

    override suspend fun addFavorite(product: ProductDomainModel) {
        val favorites = favoritesLocalDataSource.get()
        val favoritesUpdated = favorites + product
        favoritesLocalDataSource.set(favoritesUpdated)
        val user = Firebase.auth.currentUser ?: return
        val usersCollection = Firebase.firestore.collection("users")
        val userDocument = usersCollection.document(user.uid)
        if (userDocument.get().exists) {
            userDocument.update("favorite_product_ids" to FieldValue.arrayUnion(product.id))
        } else {
            userDocument.set(mapOf("favorite_product_ids" to listOf(product.id)))
        }
    }

    override suspend fun removeFavorite(id: String) {
        val favorites = favoritesLocalDataSource.get()
        val favoritesUpdated = favorites.filter { it.id != id }
        favoritesLocalDataSource.set(favoritesUpdated)
        val user = Firebase.auth.currentUser ?: return
        val usersCollection = Firebase.firestore.collection("users")
        val userDocument = usersCollection.document(user.uid)
        userDocument.update("favorite_product_ids" to FieldValue.arrayRemove(id))
    }
}