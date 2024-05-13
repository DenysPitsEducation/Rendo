package com.rendo.feature.create.advertisement.ui.image

import dev.gitlive.firebase.storage.File

internal interface ImageHelper {
    fun getFileFromPath(platformFile: Any): File
}
