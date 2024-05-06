package com.rendo.core.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.rendo.core.data.createDataStore
import com.rendo.core.dial.Dialer
import com.rendo.core.dial.DialerImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module

internal actual fun Module.nativeDependencies() {
    factory<Dialer> {
        DialerImpl(androidContext())
    }

    single<DataStore<Preferences>> {
        createDataStore(androidContext())
    }
}