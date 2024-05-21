package com.rendo.feature.home.data

import app.cash.paging.PagingSource
import app.cash.paging.PagingState
import dev.gitlive.firebase.firestore.DocumentSnapshot
import dev.gitlive.firebase.firestore.Query
import dev.gitlive.firebase.firestore.startAfter

class FirestorePagingSource<T : Any>(
    private val query: Query,
    private val mapper: (document: DocumentSnapshot) -> T,
) : PagingSource<Query, T>() {
    override fun getRefreshKey(state: PagingState<Query, T>): Query? {
        return null
    }

    override suspend fun load(params: LoadParams<Query>): LoadResult<Query, T> {
        return try {
            val currentPageQuery = (params.key ?: query).limit(params.loadSize)
            val currentPageDocuments = currentPageQuery.get().documents
            val nextPageQuery = query.startAfter(currentPageDocuments.last())
            LoadResult.Page(
                data = currentPageDocuments.map { document ->
                    mapper(document)
                },
                prevKey = null,
                nextKey = nextPageQuery
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}