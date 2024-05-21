package com.rendo.feature.home.domain.mvi

import com.arkivanov.mvikotlin.core.store.Reducer

internal class HomeReducer : Reducer<HomeState, HomeMessage> {
    override fun HomeState.reduce(
        msg: HomeMessage,
    ): HomeState = when (msg) {
        is HomeMessage.SearchInputChanged -> copy(searchInput = msg.input)
        is HomeMessage.ProductListUpdated -> copy(products = msg.products)
    }
}