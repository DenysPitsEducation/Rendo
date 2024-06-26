package com.rendo.core.dial

import android.content.Context
import android.content.Intent
import android.net.Uri

internal class DialerImpl(private val context: Context) : Dialer {
    override fun makeCall(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }
}
