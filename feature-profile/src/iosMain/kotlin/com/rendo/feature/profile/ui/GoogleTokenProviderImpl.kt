package com.rendo.feature.profile.ui

import cocoapods.GoogleSignIn.GIDConfiguration
import cocoapods.GoogleSignIn.GIDSignIn
import com.rendo.feature.profile.domain.model.GoogleToken
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIApplication
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

internal class GoogleTokenProviderImpl : GoogleTokenProvider {

    @OptIn(ExperimentalForeignApi::class)
    override suspend fun provide(): Result<GoogleToken> = suspendCoroutine { continutation ->
        try {
            val rootViewController = UIApplication.sharedApplication.keyWindow?.rootViewController
            if (rootViewController == null) {
                throw Exception()
            } else {
                GIDSignIn.sharedInstance.configuration = GIDConfiguration(clientID = "447278892031-utjebfm2htr6drcb6vt6k0dujk3ml60a.apps.googleusercontent.com")
                GIDSignIn.sharedInstance.signInWithPresentingViewController(rootViewController) { gidSignInResult, error ->
                    if (error != null) throw Exception(error.toString())

                    val idToken = gidSignInResult?.user?.idToken?.tokenString
                    val accessToken = gidSignInResult?.user?.accessToken?.tokenString
                    continutation.resume(Result.success(GoogleToken(idToken, accessToken)))
                }
            }
        } catch (e: Exception) {
            continutation.resume(Result.failure(e))
        }
    }
}