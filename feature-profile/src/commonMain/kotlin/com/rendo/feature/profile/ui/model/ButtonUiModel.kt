package com.rendo.feature.profile.ui.model

import com.rendo.feature.profile.domain.model.SignButtonType

data class ButtonUiModel(
    val type: SignButtonType,
    val text: String,
)
