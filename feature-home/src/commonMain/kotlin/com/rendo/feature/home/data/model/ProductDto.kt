package com.rendo.feature.home.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ProductDto(
    @SerialName("name")
    val name: String,
    @SerialName("image_url")
    val imageUrl: String?,
    @SerialName("price")
    val price: Double,
    @SerialName("currency")
    val currency: String,
)
