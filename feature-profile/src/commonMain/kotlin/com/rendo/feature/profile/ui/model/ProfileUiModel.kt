package com.rendo.feature.profile.ui.model

sealed class ProfileUiModel {
    data class Authorized(
        val name: String,
        val imageUrl: String?,
    ) : ProfileUiModel()

    data object Unauthorized : ProfileUiModel()
}
