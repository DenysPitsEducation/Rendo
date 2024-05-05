package com.rendo.feature.create.rent.di

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.rendo.feature.create.rent.data.repository.CreateRentRepositoryImpl
import com.rendo.feature.create.rent.domain.model.InputDomainModel
import com.rendo.feature.create.rent.domain.mvi.CreateRentAction
import com.rendo.feature.create.rent.domain.mvi.CreateRentBootstrapper
import com.rendo.feature.create.rent.domain.mvi.CreateRentExecutor
import com.rendo.feature.create.rent.domain.mvi.CreateRentReducer
import com.rendo.feature.create.rent.domain.mvi.CreateRentState
import com.rendo.feature.create.rent.domain.repository.CreateRentRepository
import com.rendo.feature.create.rent.domain.usecase.CreateRentUseCase
import com.rendo.feature.create.rent.ui.CreateRentScreenModel
import com.rendo.feature.create.rent.ui.mapper.CreateRentUiMapper
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val CREATE_RENT_STORE_NAME = "CreateRentStore"

internal expect fun Module.nativeDependencies()

fun featureCreateRentModule() = module {
    nativeDependencies()

    factory {
        CreateRentScreenModel(store = get(named(CREATE_RENT_STORE_NAME)))
    }

    factory {
        CreateRentUiMapper()
    }

    factory(named(CREATE_RENT_STORE_NAME)) {
        DefaultStoreFactory().create(
            name = CREATE_RENT_STORE_NAME,
            initialState = CreateRentState(
                productName = InputDomainModel("", null),
                productDescription = InputDomainModel("", null),
                productPrice = InputDomainModel("", null),
                ownerName = InputDomainModel("", null),
                ownerPhoneNumber = InputDomainModel("", null),
            ),
            executorFactory = { get<CreateRentExecutor>() },
            reducer = get<CreateRentReducer>(),
            bootstrapper = CreateRentBootstrapper(CreateRentAction.Init),
        )
    }

    factory<CreateRentExecutor> {
        CreateRentExecutor(
            createRentUseCase = get(),
        )
    }

    factory {
        CreateRentReducer()
    }

    factory {
        CreateRentUseCase(
            rentsRepository = get(),
        )
    }

    factory<CreateRentRepository> {
        CreateRentRepositoryImpl()
    }
}
