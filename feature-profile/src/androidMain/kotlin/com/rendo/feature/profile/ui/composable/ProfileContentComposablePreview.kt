package com.rendo.feature.profile.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.rendo.core.theme.PreviewContainer
import com.rendo.feature.profile.ui.model.ProfileUiModel

@Composable
@Preview
private fun ProfileScreenPreview() {
    PreviewContainer {
        ProfileContentComposable(
            uiModel = ProfileUiModel.Unauthorized,
            onUserInteraction = {},
        )
    }
}