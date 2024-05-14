package com.rendo.core.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RentDataModel(
    @SerialName("product_id")
    val productId: String,
    @SerialName("tenant_id")
    val tenantId: String,
    @SerialName("tenant_phone_number")
    val tenantPhoneNumber: String,
    @SerialName("owner_id")
    val ownerId: String,
    @SerialName("owner_phone_number")
    val ownerPhoneNumber: String,
    @SerialName("image_url")
    val imageUrl: String?,
    @SerialName("name")
    val name: String,
    @SerialName("status")
    val status: String,
    @SerialName("pickup_date")
    val pickupDate: String,
    @SerialName("return_date")
    val returnDate: String,
    @SerialName("price")
    val price: Double,
    @SerialName("currency")
    val currency: String,
)
