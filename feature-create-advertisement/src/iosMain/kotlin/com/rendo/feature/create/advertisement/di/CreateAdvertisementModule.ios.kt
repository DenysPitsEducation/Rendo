package com.rendo.feature.create.advertisement.di

import com.rendo.feature.create.advertisement.ui.image.ImageHelper
import com.rendo.feature.create.advertisement.ui.image.ImageHelperImpl
import org.koin.core.module.Module

internal actual fun Module.nativeDependencies() {
    factory<ImageHelper> {
        ImageHelperImpl()
    }
}