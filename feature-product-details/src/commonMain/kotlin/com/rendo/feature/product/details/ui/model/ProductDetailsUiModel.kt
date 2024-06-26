package com.rendo.feature.product.details.ui.model

internal data class ProductDetailsUiModel(
    val id: String,
    val name: String,
    val description: String,
    val imageUrls: List<String>,
    val price: String,
    val location: String,
    val isInFavorites: Boolean,
    val phoneField: TextFieldUiModel,
    val pickupDate: String,
    val returnDate: String,
    val totalPrice: String,
    val ownerUiModel: OwnerUiModel,
    val datePickerUiModel: DatePickerUiModel,
)
