package com.rendo.feature.profile.di

import com.rendo.feature.profile.ui.GoogleTokenProviderFactory
import com.rendo.feature.profile.ui.GoogleTokenProviderFactoryImpl
import org.koin.core.module.Module

internal actual fun Module.nativeDependencies() {
    factory<GoogleTokenProviderFactory> {
        GoogleTokenProviderFactoryImpl()
    }
}