package com.rendo.feature.home.data.repository

import com.rendo.core.data.model.ProductDataModel
import com.rendo.core.product.ProductDomainModel
import com.rendo.feature.home.domain.repository.HomeRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore

internal class HomeRepositoryImpl : HomeRepository {
    override suspend fun getProducts(): List<ProductDomainModel> {
        return Firebase.firestore.collection("products").get().documents.map { document ->
            val productDataModel = document.data(ProductDataModel.serializer())
            productDataModel.mapToDomainModel(document.id)
        }
    }

    private fun ProductDataModel.mapToDomainModel(id: String): ProductDomainModel {
        return ProductDomainModel(
            id = id,
            name = name,
            imageUrl = imageUrl,
            price = price,
            currency = currency,
            isInFavorites = false,
        )
    }
}