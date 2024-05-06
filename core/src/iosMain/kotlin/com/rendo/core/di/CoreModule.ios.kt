package com.rendo.core.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.rendo.core.data.createDataStore
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

    single<DataStore<Preferences>> {
        createDataStore()
    }
}