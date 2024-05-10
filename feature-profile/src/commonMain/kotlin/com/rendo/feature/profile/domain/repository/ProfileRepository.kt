package com.rendo.feature.profile.domain.repository

import com.rendo.feature.profile.domain.model.GoogleToken
import com.rendo.feature.profile.domain.model.UserDomainModel
import kotlinx.coroutines.flow.Flow

internal interface ProfileRepository {
    fun getUser(): UserDomainModel?
    fun getUserFlow(): Flow<UserDomainModel?>
    suspend fun signIn(token: GoogleToken)
    suspend fun signOut()
}