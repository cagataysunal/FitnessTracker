package com.cagataysunal.fitnesstracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cagataysunal.fitnesstracker.model.LoginRequest
import com.cagataysunal.fitnesstracker.network.FitnessApi
import com.cagataysunal.fitnesstracker.ui.theme.FitnessTrackerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FitnessTrackerTheme {
                LoginScreen()
            }
        }
    }
}

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    val loginState = rememberTextFieldState(initialText = "")
    val passwordState = rememberTextFieldState(initialText = "")
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Welcome", style = MaterialTheme.typography.displaySmall)
            OutlinedTextField(
                state = loginState,
                label = { Text("Login") }
            )
            OutlinedTextField(
                state = passwordState,
                label = { Text("Password") }
            )
            Button(onClick = {
                scope.launch {
                    try {
                        FitnessApi.service.login(
                            LoginRequest(
                                name = loginState.text.toString(),
                                password = passwordState.text.toString()
                            )
                        )
                        snackbarHostState.showSnackbar("Login Successful")
                    } catch (e: Exception) {
                        snackbarHostState.showSnackbar("Login Failed: ${e.message}")
                    }
                }
            }) {
                Text("Login")
            }
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    LoginScreen()
}