package com.rendo.feature.home.domain.mvi

internal sealed class HomeLabel {
    data class OpenProductDetails(val id: String) : HomeLabel()
}
