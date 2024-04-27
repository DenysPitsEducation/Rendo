package com.rendo.feature.home.ui.model

data class ProductUiModel(
    val id: Long,
    val name: String,
    val imageUrl: String?,
    val price: String,
    val isInFavorites: Boolean,
)
