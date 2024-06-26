package com.rendo.feature.profile.domain.model

internal data class UserDomainModel(
    val id: String,
    val name: String,
    val imageUrl: String?,
    val phoneNumber: String?,
    val isAnonymous: Boolean,
)
