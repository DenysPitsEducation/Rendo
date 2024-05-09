package com.rendo.feature.profile.ui

import com.rendo.feature.profile.domain.model.GoogleToken

interface GoogleTokenProvider {
    suspend fun provide(): Result<GoogleToken>
}