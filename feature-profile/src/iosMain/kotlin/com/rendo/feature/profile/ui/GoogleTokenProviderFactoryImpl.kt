package com.rendo.feature.profile.ui

import androidx.compose.runtime.Composable

internal class GoogleTokenProviderFactoryImpl : GoogleTokenProviderFactory {
    @Composable
    override fun create(): GoogleTokenProvider {
        return GoogleTokenProviderImpl()
    }
}