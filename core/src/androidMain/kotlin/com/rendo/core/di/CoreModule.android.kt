package com.rendo.core.di

import com.rendo.core.dial.Dialer
import com.rendo.core.dial.DialerImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module

internal actual fun Module.nativeDependencies() {
    factory<Dialer> {
        DialerImpl(androidContext())
    }
}