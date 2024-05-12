package com.rendo.feature.create.advertisement.data.repository

import com.rendo.feature.create.advertisement.data.mapper.ProductDataMapper
import com.rendo.feature.create.advertisement.domain.model.AdvertisementDomainModel
import com.rendo.feature.create.advertisement.domain.repository.CreateAdvertisementRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore

internal class CreateAdvertisementRepositoryImpl(
    private val mapper: ProductDataMapper,
) : CreateAdvertisementRepository {

    override suspend fun createAdvertisement(advertisement: AdvertisementDomainModel): Result<Unit> = runCatching {
        val database = Firebase.firestore
        val productsCollection = database.collection("products")
        val productDetailsCollection = database.collection("product_details")

        val user = Firebase.auth.currentUser ?: return Result.failure(Exception("User not found"))
        val product = mapper.mapToProductDataModel(advertisement, user)
        val productDetails = mapper.mapToProductDetailsDataModel(advertisement, user)

        database.batch().apply {
            val productDocument = productsCollection.document
            set(productDocument, product)
            val productDetailsDocument = productDetailsCollection.document(productDocument.id)
            set(productDetailsDocument, productDetails)
        }.commit()
    }
}