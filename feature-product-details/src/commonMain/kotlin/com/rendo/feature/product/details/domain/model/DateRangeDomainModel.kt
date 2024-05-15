package com.rendo.feature.product.details.domain.model

import kotlinx.datetime.LocalDate

data class DateRangeDomainModel(
    val pickupDate: LocalDate?,
    val returnDate: LocalDate?,
)