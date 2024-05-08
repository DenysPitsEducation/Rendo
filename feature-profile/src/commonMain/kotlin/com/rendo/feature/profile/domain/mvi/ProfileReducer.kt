package com.rendo.feature.profile.domain.mvi

import com.arkivanov.mvikotlin.core.store.Reducer

internal class ProfileReducer : Reducer<ProfileState, ProfileMessage> {
    override fun ProfileState.reduce(
        msg: ProfileMessage,
    ): ProfileState = when (msg) {
        is ProfileMessage.UserUpdated -> copy(user = msg.user)
    }
}