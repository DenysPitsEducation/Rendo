package com.rendo.core.product

data class ProductDomainModel(
    val id: String,
    val name: String,
    val imageUrl: String?,
    val price: Double,
    val currency: String,
    val isInFavorites: Boolean,
)
