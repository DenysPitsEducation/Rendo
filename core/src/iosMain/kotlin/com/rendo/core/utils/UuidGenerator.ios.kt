package com.rendo.core.utils

import platform.Foundation.NSUUID

actual object UuidGenerator {
    actual fun generate(): String {
        return NSUUID().UUIDString()
    }
}
