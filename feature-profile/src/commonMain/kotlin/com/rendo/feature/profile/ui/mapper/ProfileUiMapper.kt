package com.rendo.feature.profile.ui.mapper

import com.rendo.feature.profile.domain.mvi.ProfileState
import com.rendo.feature.profile.ui.model.ProfileUiModel

internal class ProfileUiMapper {
    fun mapToUiModel(state: ProfileState): ProfileUiModel = state.run {
        val profile = state.user
        return if (profile != null) {
            ProfileUiModel.Authorized(profile.name, profile.imageUrl)
        } else {
            ProfileUiModel.Unauthorized
        }
    }
}