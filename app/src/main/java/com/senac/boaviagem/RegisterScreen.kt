package com.senac.boaviagem.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.senac.boaviagem.ui.theme.registerState


@Composable
fun TelaDeRegistro(navController: NavController) {
    val navControllerState = remember(navController) { navController.registerState()}

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Registrar", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = navControllerState.login,
            onValueChange = { navControllerState.login = it },
            label = { Text("Login") }
        )
        OutlinedTextField(
            value = navControllerState.senha,
            onValueChange = { navControllerState.senha = it },
            label = { Text("Senha") },
            visualTransformation = PasswordVisualTransformation()
        )
        OutlinedTextField(
            value = navControllerState.email,
            onValueChange = { navControllerState.email = it },
            label = { Text("E-mail") }
        )

        Button(onClick = {
            navController.navigate("login") {
                popUpTo("login") { inclusive = true }
            }
        }) {
            Text("Registrar")
        }
    }
}