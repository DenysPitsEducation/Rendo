package com.rendo.core.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDataModel(
    @SerialName("name")
    val name: String,
    @SerialName("image_url")
    val imageUrl: String?,
    @SerialName("price")
    val price: Double,
    @SerialName("currency")
    val currency: String,
    @SerialName("owner_id")
    val ownerId: String,
)
