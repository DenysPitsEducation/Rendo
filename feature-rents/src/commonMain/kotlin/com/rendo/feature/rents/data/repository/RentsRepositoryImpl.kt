package com.rendo.feature.rents.data.repository

import com.rendo.core.data.model.RentDataModel
import com.rendo.feature.rents.data.mapper.RentDomainMapper
import com.rendo.feature.rents.data.mapper.RentStatusMapper
import com.rendo.feature.rents.domain.model.RentDomainModel
import com.rendo.feature.rents.domain.repository.RentsRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.Direction
import dev.gitlive.firebase.firestore.firestore
import dev.gitlive.firebase.firestore.orderBy
import dev.gitlive.firebase.firestore.where

internal class RentsRepositoryImpl(
    private val mapper: RentDomainMapper,
    private val statusMapper: RentStatusMapper,
) : RentsRepository {
    override suspend fun getRents(): Result<List<RentDomainModel>> = runCatching {
        val user = Firebase.auth.currentUser ?: throw Exception("No user found")
        val rentsCollection = Firebase.firestore.collection("rents")
        val documents = rentsCollection.where {
            any("owner_id" equalTo user.uid, "tenant_id" equalTo user.uid)
        }.orderBy("creation_timestamp", Direction.DESCENDING).get().documents
        documents.map { document ->
            val rentDataModel = document.data(RentDataModel.serializer())
            mapper.mapToDomainModel(rentDataModel, document.id, user.uid)
        }
    }

    override suspend fun acceptRent(rent: RentDomainModel): Result<Unit> = runCatching {
        updateRentStatus(rent.id, RentDomainModel.Status.ACCEPTED)
    }

    override suspend fun rejectRent(rent: RentDomainModel): Result<Unit> = runCatching {
        updateRentStatus(rent.id, RentDomainModel.Status.REJECTED)
    }

    override suspend fun cancelRent(rent: RentDomainModel): Result<Unit> = runCatching {
        updateRentStatus(rent.id, RentDomainModel.Status.CANCELLED)
    }

    override suspend fun deleteRent(rentId: String): Result<Unit> = runCatching {
        val rentsCollection = Firebase.firestore.collection("rents")
        val document = rentsCollection.document(rentId)
        document.delete()
    }

    private suspend fun updateRentStatus(rentId: String, status: RentDomainModel.Status) {
        val rentsCollection = Firebase.firestore.collection("rents")
        val document = rentsCollection.document(rentId)
        val statusDataString = statusMapper.mapToDataString(status)
        document.update("status" to statusDataString)
    }
}