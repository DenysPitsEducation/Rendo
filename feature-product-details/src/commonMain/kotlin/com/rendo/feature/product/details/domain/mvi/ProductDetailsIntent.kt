package com.rendo.feature.product.details.domain.mvi

internal sealed class ProductDetailsIntent {
    data object FavoriteButtonClicked : ProductDetailsIntent()
    data class PhoneTextFieldChanged(val text: String) : ProductDetailsIntent()
    data object RentButtonClicked : ProductDetailsIntent()
    data object ChangeDatesButtonClicked : ProductDetailsIntent()
    data object CallOwnerButtonClicked : ProductDetailsIntent()
    data class DateRangeChanged(
        val pickupDateMillis: Long?,
        val returnDateMillis: Long?,
    ) : ProductDetailsIntent()
    data class ChooseDateDialogButtonClicked(
        val pickupDateMillis: Long,
        val returnDateMillis: Long,
    ) : ProductDetailsIntent()
}
