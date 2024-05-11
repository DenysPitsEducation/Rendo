package com.rendo.feature.rents.domain.model

internal data class RentDomainModel(
    val id: String,
    val productId: String,
    val name: String,
    val imageUrl: String?,
    val status: Status,
    val dates: String,
    val price: Double,
    val currency: String,
    val phoneNumber: String,
    val type: Type,
) {
    enum class Status {
        WAITING_FOR_ACCEPTANCE, ACCEPTED, REJECTED, CANCELLED
    }

    enum class Type {
        RENT_IN, RENT_OUT
    }
}
