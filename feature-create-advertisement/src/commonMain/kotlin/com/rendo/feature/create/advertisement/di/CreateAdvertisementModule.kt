package com.rendo.feature.create.advertisement.di

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.rendo.feature.create.advertisement.data.mapper.ProductDataMapper
import com.rendo.feature.create.advertisement.data.repository.CreateAdvertisementRepositoryImpl
import com.rendo.feature.create.advertisement.domain.model.InputDomainModel
import com.rendo.feature.create.advertisement.domain.mvi.CreateAdvertisementBootstrapper
import com.rendo.feature.create.advertisement.domain.mvi.CreateAdvertisementExecutor
import com.rendo.feature.create.advertisement.domain.mvi.CreateAdvertisementReducer
import com.rendo.feature.create.advertisement.domain.mvi.CreateAdvertisementState
import com.rendo.feature.create.advertisement.domain.repository.CreateAdvertisementRepository
import com.rendo.feature.create.advertisement.domain.usecase.CreateAdvertisementUseCase
import com.rendo.feature.create.advertisement.ui.CreateAdvertisementScreenModel
import com.rendo.feature.create.advertisement.ui.mapper.CreateAdvertisementUiMapper
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val CREATE_ADVERTISEMENT_STORE_NAME = "CreateAdvertisementStore"

internal expect fun Module.nativeDependencies()

fun featureCreateAdvertisementModule() = module {
    nativeDependencies()

    factory {
        CreateAdvertisementScreenModel(store = get(named(CREATE_ADVERTISEMENT_STORE_NAME)))
    }

    factory {
        CreateAdvertisementUiMapper()
    }

    factory(named(CREATE_ADVERTISEMENT_STORE_NAME)) {
        DefaultStoreFactory().create(
            name = CREATE_ADVERTISEMENT_STORE_NAME,
            initialState = CreateAdvertisementState(
                isAuthorized = false,
                images = emptyList(),
                productName = InputDomainModel("", null),
                productDescription = InputDomainModel("", null),
                productPrice = InputDomainModel("", null),
                productLocation = InputDomainModel("", null),
                ownerName = InputDomainModel("", null),
                ownerPhoneNumber = InputDomainModel("", null),
            ),
            executorFactory = { get<CreateAdvertisementExecutor>() },
            reducer = get<CreateAdvertisementReducer>(),
            bootstrapper = CreateAdvertisementBootstrapper(),
        )
    }

    factory<CreateAdvertisementExecutor> {
        CreateAdvertisementExecutor(
            createAdvertisementUseCase = get(),
        )
    }

    factory {
        CreateAdvertisementReducer()
    }

    factory {
        CreateAdvertisementUseCase(
            repository = get(),
        )
    }

    factory<CreateAdvertisementRepository> {
        CreateAdvertisementRepositoryImpl(
            mapper = get(),
        )
    }

    factory {
        ProductDataMapper()
    }
}
