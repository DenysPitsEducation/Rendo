package com.rendo.feature.create.advertisement.ui.image

import android.net.Uri
import dev.gitlive.firebase.storage.File

class ImageHelperImpl : ImageHelper {
    override fun getFileFromPath(platformFile: Any): File {
        return File(platformFile as Uri)
    }
}