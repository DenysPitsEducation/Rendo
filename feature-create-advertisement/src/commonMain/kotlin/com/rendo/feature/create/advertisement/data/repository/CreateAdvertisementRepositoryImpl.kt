package com.rendo.feature.create.advertisement.data.repository

import com.rendo.core.data.model.ProductDataModel
import com.rendo.core.data.model.ProductDetailsDataModel
import com.rendo.core.utils.UuidGenerator
import com.rendo.feature.create.advertisement.data.mapper.ProductDataMapper
import com.rendo.feature.create.advertisement.domain.model.AdvertisementDomainModel
import com.rendo.feature.create.advertisement.domain.repository.CreateAdvertisementRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.Timestamp
import dev.gitlive.firebase.firestore.firestore
import dev.gitlive.firebase.storage.File
import dev.gitlive.firebase.storage.storage

internal class CreateAdvertisementRepositoryImpl(
    private val mapper: ProductDataMapper,
) : CreateAdvertisementRepository {

    override suspend fun createAdvertisement(advertisement: AdvertisementDomainModel): Result<Unit> = runCatching {
        val user = Firebase.auth.currentUser ?: return Result.failure(Exception("User not found"))
        val productId = UuidGenerator.generate()
        val imageUrls = uploadImagesToStorage(advertisement.images, productId)
        val product = mapper.mapToProductDataModel(advertisement, user, imageUrls.firstOrNull())
        val productDetails = mapper.mapToProductDetailsDataModel(advertisement, user, imageUrls)

        addProductToFirestore(productId, product, productDetails)
    }

    private suspend fun uploadImagesToStorage(images: List<File>, productId: String): List<String> {
        val storageRef = Firebase.storage.reference
        val imagesStorageRef = storageRef.child("products/${productId}")
        val imageUrls = mutableListOf<String>()
        images.forEachIndexed { index, image ->
            val imageStorageRef = imagesStorageRef.child("${index + 1}.jpg")
            imageStorageRef.putFile(image)
            imageUrls.add(imageStorageRef.getDownloadUrl())
        }
        return imageUrls
    }

    private suspend fun addProductToFirestore(productId: String, product: ProductDataModel, productDetails: ProductDetailsDataModel) {
        val database = Firebase.firestore
        val productsCollection = database.collection("products")
        val productDetailsCollection = database.collection("product_details")
        val productDocument = productsCollection.document(productId)
        val productDetailsDocument = productDetailsCollection.document(productId)
        database.batch().apply {
            set(productDocument, product)
            set(productDocument, mapOf("creation_timestamp" to Timestamp.ServerTimestamp), merge = true)
            set(productDetailsDocument, productDetails)
        }.commit()
    }
}