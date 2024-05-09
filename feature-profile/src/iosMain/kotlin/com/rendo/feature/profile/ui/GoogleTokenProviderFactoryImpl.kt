package com.rendo.feature.profile.ui

import androidx.compose.runtime.Composable

class GoogleTokenProviderFactoryImpl : GoogleTokenProviderFactory {
    @Composable
    override fun create(): GoogleTokenProvider {
        return GoogleTokenProviderImpl()
    }
}