package com.senac.boaviagem.ui
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.senac.boaviagem.entities.Usuario
import com.senac.boaviagem.viewmodels.UsuarioViewModel

@Composable
fun TelaDeRegistro(
    navController: NavHostController,
    state: State<Usuario>,
    usuarioViewModel: UsuarioViewModel,
    ctx: Context
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Registrar", style = MaterialTheme.typography.headlineMedium.copy(fontSize = 24.sp))

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = state.value.usuario,
            onValueChange = { usuarioViewModel.updateUsuario(it) },
            label = { Text("Usuário") },
            leadingIcon = { Icon(Icons.Filled.Person, contentDescription = null) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = state.value.senha,
            onValueChange = { usuarioViewModel.updateSenha(it) },
            label = { Text("Senha") },
            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null) },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = state.value.email,
            onValueChange = { usuarioViewModel.updateEmail(it) },
            label = { Text("E-mail") },
            leadingIcon = { Icon(Icons.Filled.Email, contentDescription = null) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (state.value.usuario.isBlank() || state.value.senha.isBlank() || state.value.email.isBlank()) {
                    Toast.makeText(ctx, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
                } else {
                    usuarioViewModel.save()
                    Toast.makeText(ctx, "Usuário salvo com sucesso", Toast.LENGTH_SHORT).show()
                    navController.navigate("login")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA726))
        ) {
            Text(text = "Salvar")
        }

        Button(
            onClick = {
                if (state.value.usuario.isBlank() || state.value.senha.isBlank() || state.value.email.isBlank()) {
                    Toast.makeText(ctx, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
                } else {
                    usuarioViewModel.saveNew()
                    Toast.makeText(ctx, "Novo usuário salvo com sucesso", Toast.LENGTH_SHORT).show()
                    navController.navigate("login")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA726))
        ) {
            Text(text = "Salvar e Novo")
        }
    }
}