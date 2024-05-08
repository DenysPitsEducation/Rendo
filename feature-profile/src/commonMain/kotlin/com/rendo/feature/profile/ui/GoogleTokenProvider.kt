package com.rendo.feature.profile.ui

interface GoogleTokenProvider {
    suspend fun provide(): Result<String>
}