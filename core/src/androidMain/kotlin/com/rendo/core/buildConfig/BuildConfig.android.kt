package com.rendo.core.buildConfig

import com.rendo.core.BuildConfig as NativeBuildConfig

actual val BuildConfig.isDebug: Boolean
    get() = NativeBuildConfig.DEBUG
