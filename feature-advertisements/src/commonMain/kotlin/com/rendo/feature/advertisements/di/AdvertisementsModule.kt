package com.rendo.feature.advertisements.di

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.rendo.feature.advertisements.data.mapper.AdvertisementDomainMapper
import com.rendo.feature.advertisements.data.repository.AdvertisementsRepositoryImpl
import com.rendo.feature.advertisements.domain.mvi.AdvertisementsAction
import com.rendo.feature.advertisements.domain.mvi.AdvertisementsBootstrapper
import com.rendo.feature.advertisements.domain.mvi.AdvertisementsExecutor
import com.rendo.feature.advertisements.domain.mvi.AdvertisementsReducer
import com.rendo.feature.advertisements.domain.mvi.AdvertisementsState
import com.rendo.feature.advertisements.domain.repository.AdvertisementsRepository
import com.rendo.feature.advertisements.domain.usecase.DeleteAdvertisementUseCase
import com.rendo.feature.advertisements.domain.usecase.GetAdvertisementsUseCase
import com.rendo.feature.advertisements.ui.AdvertisementsScreenModel
import com.rendo.feature.advertisements.ui.mapper.AdvertisementsUiMapper
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val ADVERTISEMENTS_STORE_NAME = "AdvertisementsStore"

fun featureAdvertisementsModule() = module {
    factory {
        AdvertisementsScreenModel(store = get(named(ADVERTISEMENTS_STORE_NAME)))
    }

    factory {
        AdvertisementsUiMapper()
    }

    factory(named(ADVERTISEMENTS_STORE_NAME)) {
        DefaultStoreFactory().create(
            name = ADVERTISEMENTS_STORE_NAME,
            initialState = AdvertisementsState(
                advertisements = emptyList(),
            ),
            executorFactory = { get<AdvertisementsExecutor>() },
            reducer = get<AdvertisementsReducer>(),
            bootstrapper = AdvertisementsBootstrapper(AdvertisementsAction.Init),
        )
    }

    factory<AdvertisementsExecutor> {
        AdvertisementsExecutor(
            deleteAdvertisementUseCase = get(),
            getAdvertisementsUseCase = get(),
        )
    }

    factory {
        AdvertisementsReducer()
    }

    factory {
        DeleteAdvertisementUseCase(
            rentsRepository = get(),
        )
    }

    factory {
        GetAdvertisementsUseCase(
            rentsRepository = get(),
        )
    }

    factory<AdvertisementsRepository> {
        AdvertisementsRepositoryImpl(
            mapper = get(),
        )
    }

    factory {
        AdvertisementDomainMapper()
    }
}
