package com.senac.boaviagem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.senac.boaviagem.ui.TelaDeRegistro
import com.senac.boaviagem.ui.theme.BoaViagemTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Add the TaskBar to your MainActivity layout
            // Assuming you have a LinearLayout with the id 'mainLayout' in activity_main.xml
            BoaViagemTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "login") {
                        composable("login") { LoginScreen(navController) }
                        composable("register") { TelaDeRegistro(navController) }
                        composable("userActivity/{username}") { backStackEntry ->
                            val username = backStackEntry.arguments?.getString("username") ?: "Desconhecido"
                            UserActivityScreen(username)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LoginScreen(navController: NavController) {
    LoginScreenContent(navController)
}

@Composable
fun LoginScreenContent(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.viagem),
            contentDescription = "Logo do Login",
            modifier = Modifier.fillMaxWidth()
        )

        Text("Logar", style = MaterialTheme.typography.headlineMedium)

        val username = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }

        OutlinedTextField(
            value = username.value,
            onValueChange = { username.value = it },
            label = { Text("Usuário") },
            modifier = Modifier.padding(vertical = 8.dp)
        )
        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Senha") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Button(
            onClick = {
                navController.navigate("userActivity/${username.value}")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA726)) // Laranja claro
        ) {
            Text("Logar")
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


@Composable
fun UserActivityScreen(username: String) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        MyApp()
        Text(text = "$username", style = MaterialTheme.typography.headlineSmall)
    }
}

