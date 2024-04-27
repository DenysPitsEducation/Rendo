package com.rendo.feature.home.domain.mvi

sealed class HomeAction {
    data object Init : HomeAction()
}
