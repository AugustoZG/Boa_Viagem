package com.senac.boaviagem.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.senac.boaviagem.entities.Usuario
import com.senac.boaviagem.ui.theme.loginState
import com.senac.boaviagem.viewmodels.UsuarioViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(state: State<Usuario>, usuarioViewModel: UsuarioViewModel, ctx: Context) {
//    val navControllerStateLogin= remember(navController) { navController.loginState() }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Login", style = MaterialTheme.typography.headlineMedium)


        OutlinedTextField(
            value = state.value.usuario,
            onValueChange = { usuarioViewModel.updateUsuario(it)},
            label = { Text("usuario") }
        )
        OutlinedTextField(
            value = state.value.senha,
            onValueChange = { usuarioViewModel.updateSenha(it)},
            label = { Text("Senha") },
            visualTransformation = PasswordVisualTransformation()
        )
        Button(onClick = {
            MainScope().launch {
                val user = usuarioViewModel.login(email = state.value.email, senha = state.value.senha)
                if (user != null) {
//                        navController.navigate("home")
                    usuarioViewModel.updateUsuario(user.usuario)
                    usuarioViewModel.updateEmail(user.email)
                } else{
                    Toast.makeText(ctx,
                        "Usuario ou senha errados",
                        Toast.LENGTH_SHORT).show()
                }) {
                    Text("Login", color = Color.White)

            }

        }
    }
}