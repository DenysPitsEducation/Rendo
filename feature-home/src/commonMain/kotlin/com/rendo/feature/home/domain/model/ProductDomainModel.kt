package com.rendo.feature.home.domain.model

data class ProductDomainModel(
    val id: Long,
    val name: String,
    val imageUrl: String?,
    val price: Double,
    val currency: String,
    val isInFavorites: Boolean,
)
