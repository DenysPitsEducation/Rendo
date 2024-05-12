package com.rendo.core.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OwnerDataModel(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("phone")
    val phone: String,
    @SerialName("image_url")
    val imageUrl: String?,
)