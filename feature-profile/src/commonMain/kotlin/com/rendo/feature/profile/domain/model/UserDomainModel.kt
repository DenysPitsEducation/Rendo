package com.rendo.feature.profile.domain.model

data class UserDomainModel(
    val id: String,
    val name: String,
    val imageUrl: String?,
    val phoneNumber: String?,
)
