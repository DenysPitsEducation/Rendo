package com.rendo.feature.product.details.di

import com.rendo.feature.product.details.ui.dial.Dialer
import com.rendo.feature.product.details.ui.dial.DialerImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module

internal actual fun Module.nativeDependencies() {
    factory<Dialer> {
        DialerImpl(androidContext())
    }
}