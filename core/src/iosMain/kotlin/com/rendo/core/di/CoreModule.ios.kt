package com.rendo.core.di

import com.rendo.core.dial.Dialer
import org.koin.core.module.Module

internal actual fun Module.nativeDependencies() {
    factory<Dialer> {
        object : Dialer {
            override fun makeCall(phoneNumber: String) {
                // TODO
            }
        }
    }
}