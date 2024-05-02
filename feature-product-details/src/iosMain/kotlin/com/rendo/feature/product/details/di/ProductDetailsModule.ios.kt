package com.rendo.feature.product.details.di

import com.rendo.feature.product.details.ui.dial.Dialer
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