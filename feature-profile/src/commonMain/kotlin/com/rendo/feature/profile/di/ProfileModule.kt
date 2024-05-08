package com.rendo.feature.profile.di

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.rendo.feature.profile.data.mapper.UserDomainMapper
import com.rendo.feature.profile.data.repository.ProfileRepositoryImpl
import com.rendo.feature.profile.domain.mvi.ProfileAction
import com.rendo.feature.profile.domain.mvi.ProfileBootstrapper
import com.rendo.feature.profile.domain.mvi.ProfileExecutor
import com.rendo.feature.profile.domain.mvi.ProfileReducer
import com.rendo.feature.profile.domain.mvi.ProfileState
import com.rendo.feature.profile.domain.repository.ProfileRepository
import com.rendo.feature.profile.domain.usecase.GetUserFlowUseCase
import com.rendo.feature.profile.domain.usecase.GetUserUseCase
import com.rendo.feature.profile.domain.usecase.SignInWithGoogleUseCase
import com.rendo.feature.profile.domain.usecase.SignOutUseCase
import com.rendo.feature.profile.ui.ProfileScreenModel
import com.rendo.feature.profile.ui.mapper.ProfileUiMapper
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val PROFILE_STORE_NAME = "ProfileStore"

internal expect fun Module.nativeDependencies()

fun featureProfileModule() = module {
    nativeDependencies()

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
                user = null,
            ),
            executorFactory = { get<ProfileExecutor>() },
            reducer = get<ProfileReducer>(),
            bootstrapper = ProfileBootstrapper(getUserFlowUseCase = get(), ProfileAction.Init),
        )
    }

    factory<ProfileExecutor> {
        ProfileExecutor(
            getUserUseCase = get(),
            signInUseCase = get(),
            signOutUseCase = get(),
            saveUiModeUseCase = get(),
        )
    }

    factory {
        ProfileReducer()
    }

    factory {
        GetUserUseCase(
            profileRepository = get(),
        )
    }

    factory {
        GetUserFlowUseCase(
            profileRepository = get(),
        )
    }

    factory {
        SignInWithGoogleUseCase(
            profileRepository = get(),
        )
    }

    factory {
        SignOutUseCase(
            profileRepository = get(),
        )
    }

    factory<ProfileRepository> {
        ProfileRepositoryImpl(userMapper = get())
    }

    factory {
        UserDomainMapper()
    }
}
