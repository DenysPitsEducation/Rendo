package com.rendo.feature.home.domain.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.rendo.feature.home.domain.usecase.GetProductsUseCase

internal class HomeExecutor(
    private val getProductsUseCase: GetProductsUseCase,
) : CoroutineExecutor<HomeIntent, HomeAction, HomeState, HomeMessage, HomeLabel>() {
    override fun executeAction(action: HomeAction, getState: () -> HomeState) = when (action) {
        HomeAction.Init -> {
            // TODO Pits:  
        }
    }

    override fun executeIntent(intent: HomeIntent, getState: () -> HomeState) = when (intent) {
        is HomeIntent.SearchInputChanged -> {
            // TODO Pits:  
        }
    }
}