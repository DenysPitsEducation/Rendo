package com.rendo.feature.profile.domain.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.rendo.feature.profile.domain.usecase.GetUserFlowUseCase
import kotlinx.coroutines.launch

internal class ProfileBootstrapper(
    private val getUserFlowUseCase: GetUserFlowUseCase,
    private vararg val actions: ProfileAction,
) : CoroutineBootstrapper<ProfileAction>() {

    override fun invoke() {
        actions.forEach(::dispatch)
        collectUser()
    }

    private fun collectUser() {
        scope.launch {
            getUserFlowUseCase.invoke().collect { user ->
                dispatch(ProfileAction.UserUpdated(user))
            }
        }
    }
}
