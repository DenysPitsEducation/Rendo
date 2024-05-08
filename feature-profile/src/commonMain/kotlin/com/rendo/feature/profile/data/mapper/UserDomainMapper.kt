package com.rendo.feature.profile.data.mapper

import com.rendo.feature.profile.domain.model.UserDomainModel
import dev.gitlive.firebase.auth.FirebaseUser

class UserDomainMapper {
    fun mapToDomainModel(firebaseUser: FirebaseUser?): UserDomainModel? = firebaseUser?.run {
        return UserDomainModel(
            id = uid,
            name = displayName.orEmpty(),
            imageUrl = photoURL,
            phoneNumber = phoneNumber,
        )
    }
}