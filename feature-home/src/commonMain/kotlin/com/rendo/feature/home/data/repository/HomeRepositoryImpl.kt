package com.rendo.feature.home.data.repository

import com.rendo.core.data.mapper.ProductDomainMapper
import com.rendo.core.data.model.ProductDataModel
import com.rendo.core.product.ProductDomainModel
import com.rendo.feature.home.domain.repository.HomeRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore

internal class HomeRepositoryImpl(
    private val mapper: ProductDomainMapper,
) : HomeRepository {
    override suspend fun getProducts(): List<ProductDomainModel> {
        return Firebase.firestore.collection("products").get().documents.map { document ->
            val productDataModel = document.data(ProductDataModel.serializer())
            mapper.mapToDomainModel(productDataModel, document.id)
        }
    }
}