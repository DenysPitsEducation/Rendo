package com.rendo.feature.rents.domain.mvi

internal sealed class RentsLabel {
    data class OpenProductDetails(val id: Long) : RentsLabel()
    data class DialNumber(val phoneNumber: String) : RentsLabel()
}
