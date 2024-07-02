package com.senac.boaviagem.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.senac.boaviagem.R
import com.senac.boaviagem.entities.Usuario
import com.senac.boaviagem.viewmodels.UsuarioViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navController: NavHostController,
    state: State<Usuario>,
    usuarioViewModel: UsuarioViewModel,
    ctx: Context
) {
    Column(modifier = Modifier
        .fillMaxHeight()
        .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Image(
            painter = painterResource(id = R.drawable.viagem),
            contentDescription = "Logo do Login",
            modifier = Modifier.fillMaxWidth()
        )

        Text("Login", style = MaterialTheme.typography.headlineMedium)
        OutlinedTextField(
            value = state.value.usuario,
            onValueChange = { usuarioViewModel.updateUsuario(it)},
            label = { Text("usuario") },
            modifier = Modifier.padding(vertical = 8.dp)
        )
        OutlinedTextField(
            value = state.value.senha,
            onValueChange = { usuarioViewModel.updateSenha(it)},
            label = { Text("Senha") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Button(
            onClick = {
                MainScope().launch {
                val user = usuarioViewModel.login(email = state.value.email, senha = state.value.senha)
                if (user != null) {
                    navController.navigate("logado")
                    usuarioViewModel.updateUsuario(user.usuario)
                    usuarioViewModel.updateEmail(user.email)
                } else{
                    Toast.makeText(ctx,
                        "Usuario ou senha errados",
                        Toast.LENGTH_SHORT).show() }
                                 }
                      },
                modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA726))) {
                    Text("logar", color = Color.White)
            }
        Button(
            onClick = { navController.navigate("register") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF35007C))
        ) {
            Text("Registrar")
        }

        }
    }