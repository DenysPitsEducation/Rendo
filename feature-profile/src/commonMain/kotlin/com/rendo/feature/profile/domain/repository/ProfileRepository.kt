package com.rendo.feature.profile.domain.repository

import com.rendo.feature.profile.domain.model.UserDomainModel
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun getUser(): UserDomainModel?
    fun getUserFlow(): Flow<UserDomainModel?>
    suspend fun signIn(idToken: String)
    suspend fun signOut()
}