package com.rendo.core.buildConfig

import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class)
actual val BuildConfig.isDebug: Boolean
    get() = Platform.isDebugBinary
