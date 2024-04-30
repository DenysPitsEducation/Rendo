package com.rendo.feature.home.domain.mvi

sealed class HomeLabel {
    data class OpenProductDetails(val id: Long) : HomeLabel()
}
