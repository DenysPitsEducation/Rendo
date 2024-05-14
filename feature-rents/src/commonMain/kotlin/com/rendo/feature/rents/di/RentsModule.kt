package com.rendo.feature.rents.di

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.rendo.feature.rents.data.mapper.RentDomainMapper
import com.rendo.feature.rents.data.mapper.RentStatusMapper
import com.rendo.feature.rents.data.repository.RentsRepositoryImpl
import com.rendo.feature.rents.domain.mvi.RentsAction
import com.rendo.feature.rents.domain.mvi.RentsBootstrapper
import com.rendo.feature.rents.domain.mvi.RentsExecutor
import com.rendo.feature.rents.domain.mvi.RentsReducer
import com.rendo.feature.rents.domain.mvi.RentsState
import com.rendo.feature.rents.domain.repository.RentsRepository
import com.rendo.feature.rents.domain.usecase.AcceptRentUseCase
import com.rendo.feature.rents.domain.usecase.CancelRentUseCase
import com.rendo.feature.rents.domain.usecase.DeleteRentUseCase
import com.rendo.feature.rents.domain.usecase.GetRentsUseCase
import com.rendo.feature.rents.domain.usecase.RejectRentUseCase
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
            acceptRentUseCase = get(),
            rejectRentUseCase = get(),
            cancelRentUseCase = get(),
            deleteRentUseCase = get(),
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

    factory {
        AcceptRentUseCase(
            rentsRepository = get(),
        )
    }

    factory {
        RejectRentUseCase(
            rentsRepository = get(),
        )
    }

    factory {
        CancelRentUseCase(
            rentsRepository = get(),
        )
    }

    factory {
        DeleteRentUseCase(
            rentsRepository = get(),
        )
    }

    factory<RentsRepository> {
        RentsRepositoryImpl(
            mapper = get(),
            statusMapper = get(),
        )
    }

    factory {
        RentDomainMapper(statusMapper = get())
    }

    factory {
        RentStatusMapper()
    }
}
