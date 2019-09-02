/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.compose.jetsurvey.signinsignup.ui.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.compose.jetsurvey.signinsignup.data.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class SignInViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _viewState = MutableStateFlow<SignInViewState>(SignInViewState.Uninitialized)
    val viewState: Flow<SignInViewState> = _viewState

    fun initialize(email: String?) {
        _viewState.value = SignInViewState.Loaded(email, isInvalidPassword = false)
    }

    fun signIn(
        email: String,
        password: String,
        onSignInComplete: () -> Unit
    ) {
        (_viewState.value as? SignInViewState.Loaded)?.let {
            _viewState.value = it.copy(isInvalidPassword = false)
        }
        if (UserRepository.signIn(email, password))
            onSignInComplete()
        else {
            (_viewState.value as? SignInViewState.Loaded)?.let {
                _viewState.value = it.copy(isInvalidPassword = true)
            }
        }
    }

    fun signInAsGuest(
        onSignInComplete: () -> Unit,
    ) {
        UserRepository.signInAsGuest()
        onSignInComplete()
    }
}

class SignInViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {
            return SignInViewModel(UserRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
