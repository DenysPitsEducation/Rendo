package com.rendo.feature.product.details.domain.mvi

sealed class ProductDetailsLabel {
    data object OpenDateRangePicker : ProductDetailsLabel()
    data class DialNumber(val number: String): ProductDetailsLabel()
    data object ShowSuccessfulRentDialog : ProductDetailsLabel()
}
