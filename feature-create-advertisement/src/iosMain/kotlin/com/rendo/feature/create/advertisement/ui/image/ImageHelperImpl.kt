package com.rendo.feature.create.advertisement.ui.image

import dev.gitlive.firebase.storage.File
import platform.Foundation.NSURL

class ImageHelperImpl : ImageHelper {
    override fun getFileFromPath(platformFile: Any): File {
        return File(platformFile as NSURL)
    }
}
