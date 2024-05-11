package com.rendo.feature.product.details.domain.model

import kotlinx.datetime.LocalDate

internal data class ProductDetailsDomainModel(
    val id: String,
    val name: String,
    val description: String,
    val imageUrls: List<String>,
    val price: Double,
    val currency: String,
    val isInFavorites: Boolean,
    val pickupDate: LocalDate,
    val returnDate: LocalDate,
    val prohibitedDates: List<LocalDate>,
    val totalPrice: Double,
    val owner: OwnerDomainModel,
)
