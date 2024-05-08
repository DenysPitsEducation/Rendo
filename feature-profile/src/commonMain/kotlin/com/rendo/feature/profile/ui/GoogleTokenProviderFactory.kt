package com.rendo.feature.profile.ui

import androidx.compose.runtime.Composable

interface GoogleTokenProviderFactory {
    @Composable
    fun create(): GoogleTokenProvider
}