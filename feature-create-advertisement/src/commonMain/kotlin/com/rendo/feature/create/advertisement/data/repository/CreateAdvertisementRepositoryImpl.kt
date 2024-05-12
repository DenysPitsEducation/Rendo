package com.rendo.feature.create.advertisement.data.repository

import com.rendo.feature.create.advertisement.domain.repository.CreateAdvertisementRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore

internal class CreateAdvertisementRepositoryImpl : CreateAdvertisementRepository {
    override fun createRent() {
        val database = Firebase.firestore
    }
}