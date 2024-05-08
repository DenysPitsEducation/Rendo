package com.rendo.feature.profile.di

import androidx.credentials.CredentialManager
import com.rendo.feature.profile.ui.GoogleTokenProviderFactory
import com.rendo.feature.profile.ui.GoogleTokenProviderFactoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module

internal actual fun Module.nativeDependencies() {
    factory<CredentialManager> {
        CredentialManager.create(androidContext())
    }

    factory<GoogleTokenProviderFactory> {
        GoogleTokenProviderFactoryImpl(credentialManager = get())
    }
}