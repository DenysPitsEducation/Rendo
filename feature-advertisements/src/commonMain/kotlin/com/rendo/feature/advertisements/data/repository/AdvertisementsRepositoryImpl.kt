package com.rendo.feature.advertisements.data.repository

import com.rendo.core.data.model.ProductDataModel
import com.rendo.feature.advertisements.data.mapper.AdvertisementDomainMapper
import com.rendo.feature.advertisements.domain.model.AdvertisementDomainModel
import com.rendo.feature.advertisements.domain.repository.AdvertisementsRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.Direction
import dev.gitlive.firebase.firestore.firestore
import dev.gitlive.firebase.firestore.orderBy
import dev.gitlive.firebase.firestore.where
import dev.gitlive.firebase.storage.storage

internal class AdvertisementsRepositoryImpl(
    private val mapper: AdvertisementDomainMapper,
) : AdvertisementsRepository {
    override suspend fun getAdvertisements(): List<AdvertisementDomainModel> {
        val user = Firebase.auth.currentUser ?: return emptyList()
        val collection = Firebase.firestore.collection("products")
        val products = collection.where {
            all("owner_id" equalTo user.uid)
        }.orderBy("creation_timestamp", Direction.DESCENDING).get()
        return products.documents.map { documentSnapshot ->
            val productDataModel = documentSnapshot.data(ProductDataModel.serializer())
            mapper.mapToDomainModel(productDataModel, documentSnapshot.id)
        }
    }

    override suspend fun deleteAdvertisement(id: String): Result<Unit> = runCatching {
        val database = Firebase.firestore
        val productsCollection = database.collection("products")
        val productDetailsCollection = database.collection("product_details")
        database.batch().apply {
            delete(productsCollection.document(id))
            delete(productDetailsCollection.document(id))
        }.commit()
        val storage = Firebase.storage.reference
        val imagesDirectory = storage.child("products/$id")
        imagesDirectory.listAll().items.forEach {
            it.delete()
        }
    }
}