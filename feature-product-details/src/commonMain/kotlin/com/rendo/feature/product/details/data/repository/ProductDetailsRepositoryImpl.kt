package com.rendo.feature.product.details.data.repository

import com.rendo.core.data.model.ProductDetailsDataModel
import com.rendo.feature.product.details.data.mapper.ProductDetailsDomainMapper
import com.rendo.feature.product.details.domain.model.ProductDetailsDomainModel
import com.rendo.feature.product.details.domain.repository.ProductDetailsRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore

internal class ProductDetailsRepositoryImpl(
    private val mapper: ProductDetailsDomainMapper,
) : ProductDetailsRepository {

    override suspend fun getProductDetails(id: String): Result<ProductDetailsDomainModel> = runCatching {
        val productDetailsDocument = Firebase.firestore.collection("product_details").document(id)
        val productDetailsDataModel = productDetailsDocument.get().data(ProductDetailsDataModel.serializer())
        mapper.mapToDomainModel(productDetailsDataModel, id)
    }
}