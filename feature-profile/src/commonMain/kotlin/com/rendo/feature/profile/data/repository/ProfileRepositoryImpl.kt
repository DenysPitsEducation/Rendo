package com.rendo.feature.profile.data.repository

import com.rendo.feature.profile.data.mapper.UserDomainMapper
import com.rendo.feature.profile.domain.model.GoogleToken
import com.rendo.feature.profile.domain.model.UserDomainModel
import com.rendo.feature.profile.domain.repository.ProfileRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.GoogleAuthProvider
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class ProfileRepositoryImpl(
    private val userMapper: UserDomainMapper,
) : ProfileRepository {
    override fun getUser(): UserDomainModel? {
        val firebaseUser = Firebase.auth.currentUser
        return userMapper.mapToDomainModel(firebaseUser)
    }

    override fun getUserFlow(): Flow<UserDomainModel?> {
        return Firebase.auth.authStateChanged.map { firebaseUser ->
            userMapper.mapToDomainModel(firebaseUser)
        }
    }

    override suspend fun signIn(token: GoogleToken) {
        val authCredential = GoogleAuthProvider.credential(idToken = token.idToken, accessToken = token.accessToken)
        Firebase.auth.signInWithCredential(authCredential)
    }

    override suspend fun signOut() {
        Firebase.auth.signInAnonymously()
    }
}