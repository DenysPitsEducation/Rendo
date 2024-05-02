package com.rendo.feature.product.details.domain.model

import kotlinx.datetime.LocalDate

data class ProductDetailsDomainModel(
    val id: Long,
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
