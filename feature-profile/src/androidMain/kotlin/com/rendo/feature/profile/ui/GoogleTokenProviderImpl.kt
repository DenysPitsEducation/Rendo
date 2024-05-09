package com.rendo.feature.profile.ui

import android.content.Context
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.rendo.feature.profile.domain.model.GoogleToken

class GoogleTokenProviderImpl(
    private val activityContext: Context,
    private val credentialManager: CredentialManager,
) : GoogleTokenProvider {
    override suspend fun provide(): Result<GoogleToken> {
        return runCatching {
            val credential = getCredential()
            if (credential is GoogleIdTokenCredential) {
                // TODO Pits: accessToken?
                GoogleToken(idToken = credential.idToken, accessToken = null)
            } else {
                throw Exception()
            }
        }
    }

    private suspend fun getCredential(): Credential {
        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId("447278892031-amppnku3rcprn859pm4ll5an59gumf7h.apps.googleusercontent.com")
            .build()
        val request: GetCredentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()
        val credentialResponse = credentialManager.getCredential(
            request = request,
            context = activityContext,
        )
        return credentialResponse.credential
    }
}