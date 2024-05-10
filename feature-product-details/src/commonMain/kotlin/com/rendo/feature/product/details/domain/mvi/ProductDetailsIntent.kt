package com.rendo.feature.product.details.domain.mvi

internal sealed class ProductDetailsIntent {
    data object FavoriteButtonClicked : ProductDetailsIntent()
    data object RentButtonClicked : ProductDetailsIntent()
    data object ChangeDatesButtonClicked : ProductDetailsIntent()
    data object CallOwnerButtonClicked : ProductDetailsIntent()
    data class ChooseDateDialogButtonClicked(
        val pickupDateMillis: Long,
        val returnDateMillis: Long,
    ) : ProductDetailsIntent()
}
