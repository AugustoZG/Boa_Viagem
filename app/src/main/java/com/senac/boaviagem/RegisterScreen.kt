package com.senac.boaviagem.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.senac.boaviagem.entities.Usuario
import com.senac.boaviagem.viewmodels.UsuarioViewModel

@Composable
fun TelaDeRegistro(state: State<Usuario>, usuarioViewModel: UsuarioViewModel, ctx: Context) {
//    val navControllerState = remember(navController) { navController.registerState()}


    Column(modifier = Modifier.padding(16.dp)) {
        Text("Registrar", style = MaterialTheme.typography.headlineMedium)

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
        OutlinedTextField(
            value = state.value.email,
            onValueChange = { usuarioViewModel.updateEmail(it) },
            label = { Text("E-mail") }
        )
        Button(onClick = {
               usuarioViewModel.save()
               Toast.makeText(ctx,
                    "Product saved",
                    Toast.LENGTH_SHORT).show()
            }) {
                Text(text = "Save")
            }
        Button(onClick = {
                usuarioViewModel.saveNew()
            }) {
                Text(text = "Save/New")
            }
    }
}