package com.rendo.feature.product.details.ui.model

internal data class ProductDetailsUiModel(
    val id: Long,
    val name: String,
    val description: String,
    val imageUrls: List<String>,
    val price: String,
    val isInFavorites: Boolean,
    val pickupDate: String,
    val returnDate: String,
    val totalPrice: String,
    val ownerUiModel: OwnerUiModel,
    val datePickerUiModel: DatePickerUiModel,
)
