package com.rendo.app.di

import com.rendo.app.AppInitializer
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

class AppInitializerHelper : KoinComponent {
    private val appInitializer: AppInitializer by inject()

    fun initialize() {
        appInitializer.initialize()
    }
}

fun initKoin() {
    startKoin {
        modules(getAllKoinModules())
    }
}