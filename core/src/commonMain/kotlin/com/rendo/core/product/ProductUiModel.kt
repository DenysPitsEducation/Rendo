package com.rendo.core.product

data class ProductUiModel(
    val id: Long,
    val name: String,
    val imageUrl: String?,
    val price: String,
    val isInFavorites: Boolean,
)
