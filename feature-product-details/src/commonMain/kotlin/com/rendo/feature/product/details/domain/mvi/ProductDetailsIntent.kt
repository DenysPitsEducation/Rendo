package com.rendo.feature.product.details.domain.mvi

sealed class ProductDetailsIntent {
    data object FavoriteStateChanged : ProductDetailsIntent()
    data object RentButtonClicked : ProductDetailsIntent()
    data object ChangeDatesButtonClicked : ProductDetailsIntent()
    data object CallOwnerButtonClicked : ProductDetailsIntent()
    data class ChooseDateDialogButtonClicked(
        val pickupDateMillis: Long,
        val returnDateMillis: Long,
    ) : ProductDetailsIntent()
}
