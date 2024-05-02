package com.rendo.app.di

import org.koin.core.context.startKoin

fun initKoin(){
    startKoin {
        modules(getAllKoinModules())
    }
}