package com.rendo.feature.rents.di

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.rendo.feature.rents.data.repository.RentsRepositoryImpl
import com.rendo.feature.rents.domain.mvi.RentsAction
import com.rendo.feature.rents.domain.mvi.RentsBootstrapper
import com.rendo.feature.rents.domain.mvi.RentsExecutor
import com.rendo.feature.rents.domain.mvi.RentsReducer
import com.rendo.feature.rents.domain.mvi.RentsState
import com.rendo.feature.rents.domain.repository.RentsRepository
import com.rendo.feature.rents.domain.usecase.GetRentsUseCase
import com.rendo.feature.rents.ui.RentsScreenModel
import com.rendo.feature.rents.ui.mapper.RentsUiMapper
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val RENTS_STORE_NAME = "RentsStore"

fun featureRentsModule() = module {
    factory {
        RentsScreenModel(store = get(named(RENTS_STORE_NAME)))
    }

    factory {
        RentsUiMapper()
    }

    factory(named(RENTS_STORE_NAME)) {
        DefaultStoreFactory().create(
            name = RENTS_STORE_NAME,
            initialState = RentsState(
                rents = emptyList(),
            ),
            executorFactory = { get<RentsExecutor>() },
            reducer = get<RentsReducer>(),
            bootstrapper = RentsBootstrapper(RentsAction.Init),
        )
    }

    factory<RentsExecutor> {
        RentsExecutor(
            getRentsUseCase = get(),
        )
    }

    factory {
        RentsReducer()
    }

    factory {
        GetRentsUseCase(
            rentsRepository = get(),
        )
    }

    factory<RentsRepository> {
        RentsRepositoryImpl()
    }
}
