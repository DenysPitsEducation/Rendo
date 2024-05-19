package com.rendo.core.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDetailsDataModel(
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String,
    @SerialName("image_urls")
    val imageUrls: List<String>,
    @SerialName("price")
    val price: Double,
    @SerialName("currency")
    val currency: String,
    @SerialName("location")
    val location: String,
    @SerialName("prohibited_dates")
    val prohibitedDates: List<String>,
    @SerialName("owner")
    val owner: OwnerDataModel,
)
