package com.rendo.feature.product.details.data.repository

import com.raedghazal.kotlinx_datetime_ext.LocalDateTimeFormatter
import com.raedghazal.kotlinx_datetime_ext.Locale
import com.rendo.core.data.model.ProductDetailsDataModel
import com.rendo.core.utils.iterator
import com.rendo.feature.product.details.data.mapper.ProductDetailsDomainMapper
import com.rendo.feature.product.details.data.mapper.RentDataMapper
import com.rendo.feature.product.details.domain.model.ProductDetailsDomainModel
import com.rendo.feature.product.details.domain.repository.ProductDetailsRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.FieldValue
import dev.gitlive.firebase.firestore.Timestamp
import dev.gitlive.firebase.firestore.firestore

internal class ProductDetailsRepositoryImpl(
    private val productMapper: ProductDetailsDomainMapper,
    private val rentMapper: RentDataMapper,
) : ProductDetailsRepository {

    override suspend fun getProductDetails(id: String): Result<ProductDetailsDomainModel> = runCatching {
        val productDetailsDocument = Firebase.firestore.collection("product_details").document(id)
        val productDetailsDataModel = productDetailsDocument.get().data(ProductDetailsDataModel.serializer())
        productMapper.mapToDomainModel(productDetailsDataModel, id)
    }

    override suspend fun createProductRent(productDetails: ProductDetailsDomainModel, tenantPhoneNumber: String): Result<Unit> = runCatching {
        val firestore = Firebase.firestore
        val user = Firebase.auth.currentUser ?: throw Exception("User not found")
        val rentDataModel = rentMapper.mapToRentDataModel(productDetails, tenantPhoneNumber, user)
        val rentsCollection = firestore.collection("rents")
        val rentsDocument = rentsCollection.document
        val productDetailsCollection = firestore.collection("product_details")
        val productDetailsDocument = productDetailsCollection.document(productDetails.id)
        val formatter = LocalDateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.default())
        val prohibitedDatesFormatted = mutableListOf<String>()
        for(prohibitedDate in productDetails.pickupDate..productDetails.returnDate) {
            prohibitedDatesFormatted.add(formatter.format(prohibitedDate))
        }
        firestore.batch().apply {
            set(rentsDocument, rentDataModel)
            set(rentsDocument, mapOf("creation_timestamp" to Timestamp.ServerTimestamp), merge = true)
            update(productDetailsDocument, "prohibited_dates" to FieldValue.arrayUnion(*prohibitedDatesFormatted.toTypedArray()))
        }.commit()
    }
}