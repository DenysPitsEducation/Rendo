package com.rendo.feature.rents.ui.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.rendo.feature.rents.domain.model.DropdownAction

internal data class DropdownItemUiModel(
    val action: DropdownAction,
    val icon: ImageVector,
    val text: String,
)
