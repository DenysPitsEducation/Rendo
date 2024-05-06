package com.rendo.feature.profile.di

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.rendo.feature.profile.data.repository.ProfileRepositoryImpl
import com.rendo.feature.profile.domain.mvi.ProfileAction
import com.rendo.feature.profile.domain.mvi.ProfileBootstrapper
import com.rendo.feature.profile.domain.mvi.ProfileExecutor
import com.rendo.feature.profile.domain.mvi.ProfileReducer
import com.rendo.feature.profile.domain.mvi.ProfileState
import com.rendo.feature.profile.domain.repository.ProfileRepository
import com.rendo.feature.profile.domain.usecase.GetProfileUseCase
import com.rendo.feature.profile.ui.ProfileScreenModel
import com.rendo.feature.profile.ui.mapper.ProfileUiMapper
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val PROFILE_STORE_NAME = "ProfileStore"

fun featureProfileModule() = module {
    factory {
        ProfileScreenModel(store = get(named(PROFILE_STORE_NAME)))
    }

    factory {
        ProfileUiMapper()
    }

    factory(named(PROFILE_STORE_NAME)) {
        DefaultStoreFactory().create(
            name = PROFILE_STORE_NAME,
            initialState = ProfileState(
                profile = null,
            ),
            executorFactory = { get<ProfileExecutor>() },
            reducer = get<ProfileReducer>(),
            bootstrapper = ProfileBootstrapper(ProfileAction.Init),
        )
    }

    factory<ProfileExecutor> {
        ProfileExecutor(
            getProfileUseCase = get(),
            saveUiModeUseCase = get(),
        )
    }

    factory {
        ProfileReducer()
    }

    factory {
        GetProfileUseCase(
            profileRepository = get(),
        )
    }

    factory<ProfileRepository> {
        ProfileRepositoryImpl()
    }
}
