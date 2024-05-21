package com.rendo.feature.home.data.repository

import com.rendo.core.data.mapper.ProductDomainMapper
import com.rendo.core.data.model.ProductDataModel
import com.rendo.core.product.ProductDomainModel
import com.rendo.feature.home.domain.repository.HomeRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.Direction
import dev.gitlive.firebase.firestore.firestore
import dev.gitlive.firebase.firestore.orderBy
import dev.gitlive.firebase.firestore.startAfter

internal class HomeRepositoryImpl(
    private val mapper: ProductDomainMapper,
) : HomeRepository {
    override suspend fun getProducts(lastVisibleProductId: String?): List<ProductDomainModel> {
        val collection = Firebase.firestore.collection("products")
        return collection
            .orderBy("creation_timestamp", Direction.DESCENDING)
            .run {
                if (lastVisibleProductId != null) {
                    startAfter(collection.document(lastVisibleProductId).get())
                } else {
                    this
                }
            }
            .limit(20)
            .get().documents.map { document ->
                val productDataModel = document.data(ProductDataModel.serializer())
                mapper.mapToDomainModel(productDataModel, document.id)
            }
    }
}