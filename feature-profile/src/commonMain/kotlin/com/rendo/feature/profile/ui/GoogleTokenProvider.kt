package com.rendo.feature.profile.ui

import com.rendo.feature.profile.domain.model.GoogleToken

internal interface GoogleTokenProvider {
    suspend fun provide(): Result<GoogleToken>
}