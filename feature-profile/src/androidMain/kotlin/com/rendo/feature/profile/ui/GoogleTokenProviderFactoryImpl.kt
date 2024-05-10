package com.rendo.feature.profile.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.CredentialManager

internal class GoogleTokenProviderFactoryImpl(
    private val credentialManager: CredentialManager
) : GoogleTokenProviderFactory {
    @Composable
    override fun create(): GoogleTokenProvider {
        val context = LocalContext.current
        return GoogleTokenProviderImpl(
            activityContext = context,
            credentialManager = credentialManager,
        )
    }
}
