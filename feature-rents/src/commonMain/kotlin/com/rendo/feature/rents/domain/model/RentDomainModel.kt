package com.rendo.feature.rents.domain.model

data class RentDomainModel(
    val id: Long,
    val productId: Long,
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