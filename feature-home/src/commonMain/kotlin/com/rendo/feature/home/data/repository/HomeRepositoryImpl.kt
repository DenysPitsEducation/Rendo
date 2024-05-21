package com.rendo.feature.home.data.repository

import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import com.rendo.core.data.mapper.ProductDomainMapper
import com.rendo.core.data.model.ProductDataModel
import com.rendo.core.product.ProductDomainModel
import com.rendo.feature.home.data.FirestorePagingSource
import com.rendo.feature.home.domain.repository.HomeRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.Direction
import dev.gitlive.firebase.firestore.Query
import dev.gitlive.firebase.firestore.firestore
import dev.gitlive.firebase.firestore.orderBy
import kotlinx.coroutines.flow.Flow

internal class HomeRepositoryImpl(
    private val mapper: ProductDomainMapper,
) : HomeRepository {

    override suspend fun getProducts(): Flow<PagingData<ProductDomainModel>> {
        return Pager(
            config = PagingConfig(pageSize = 5, initialLoadSize = 5, enablePlaceholders = false),
            pagingSourceFactory = {
                FirestorePagingSource(
                    query = productQuery,
                    mapper = { document ->
                        val productDataModel = document.data(ProductDataModel.serializer())
                        mapper.mapToDomainModel(productDataModel, document.id)
                    },
                )
            }
        ).flow
    }

    private val productQuery: Query = Firebase.firestore.collection("products")
        .orderBy("creation_timestamp", Direction.DESCENDING)
}