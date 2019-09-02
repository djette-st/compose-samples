package com.example.compose.jetsurvey.signinsignup.ui.signin

sealed class SignInViewState {
    object Uninitialized : SignInViewState()
    data class Loaded(
        val email: String?,
        val isInvalidPassword: Boolean
    ) : SignInViewState()
}
