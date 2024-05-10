package com.rendo.feature.profile.ui

import androidx.compose.runtime.Composable

internal interface GoogleTokenProviderFactory {
    @Composable
    fun create(): GoogleTokenProvider
}