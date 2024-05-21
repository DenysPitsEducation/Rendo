package com.rendo.core.product

data class ProductUiModel(
    val id: String,
    val name: String,
    val imageUrl: String?,
    val price: String,
    val isInFavorites: Boolean,
    val payload: ProductDomainModel? = null,
)
