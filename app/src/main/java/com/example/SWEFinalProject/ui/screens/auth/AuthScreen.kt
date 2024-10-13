package com.example.SWEFinalProject.ui.screens.auth

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.SWEFinalProject.MainActivity

@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val authUiState by viewModel.uiState.collectAsStateWithLifecycle()
    var isSignIn by remember { mutableStateOf(true) }

    if (isSignIn) {
        SignInScreen(
            onSignInClick = { email, password ->
                viewModel.signIn(user = email, password = password)
            },
            onSignUpClick = { isSignIn = false }
        )
    } else {
        SignUpScreen(
            onSignUpClick = { email, password ->
                viewModel.signUp(user = email, password = password)
            },
            onSignInClick = { isSignIn = true }
        )
    }

    LaunchedEffect(authUiState) {
        if (authUiState == AuthUiState.Success) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

}