package com.example.SWEFinalProject.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.SWEFinalProject.data.repository.Repository
import com.example.SWEFinalProject.util.Result
import com.example.SWEFinalProject.util.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val viewModelState = MutableStateFlow<AuthUiState>(AuthUiState.Loading)

    //Immutable UI state exposed to the UI
    val uiState = viewModelState.stateIn(
        scope = viewModelScope,
        initialValue = viewModelState.value,
        started = SharingStarted.WhileSubscribed(5_000),
    )

    fun signIn(user: String, password: String) {

        viewModelScope.launch {
            repository.signIn(user, password).asResult().collect { result ->
                when (result) {
                    is Result.Error -> {
                        viewModelState.value = AuthUiState.Error(error = result.exception)
                        //viewModelState.value = AuthUiState.Success

                    }

                    Result.Loading -> {
                        viewModelState.value = AuthUiState.Loading
                    }

                    is Result.Success -> {
                        viewModelState.value = AuthUiState.Success
                    }
                }
            }
        }
    }

    fun signUp(user: String, password: String, fname: String, lname: String, phone: String) {

        viewModelScope.launch {
            viewModelState.value = AuthUiState.Success

            repository.signUp(user, password, phone, fname, lname).asResult().collect { result ->
                when (result) {
                    is Result.Error -> {
                        viewModelState.value = AuthUiState.Error(error = result.exception)
                        //viewModelState.value = AuthUiState.Success
                    }

                    Result.Loading -> {
                        viewModelState.value = AuthUiState.Loading
                    }

                    is Result.Success -> {
                        viewModelState.value = AuthUiState.Success
                    }
                }
            }
        }
    }

}


sealed interface AuthUiState {
    data object Loading : AuthUiState
    data class Error(val error: Throwable?) : AuthUiState
    data object Success : AuthUiState

}