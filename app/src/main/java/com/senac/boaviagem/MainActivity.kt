package com.senac.boaviagem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.senac.boaviagem.Classes.ViagemViewModel
import com.senac.boaviagem.Consulta.ConsultaViagem
import com.senac.boaviagem.bd.AppDatabase
import com.senac.boaviagem.ui.LoginScreen
import com.senac.boaviagem.ui.TelaDeRegistro
import com.senac.boaviagem.ui.theme.BoaViagemTheme
import com.senac.boaviagem.viewmodels.UsuarioViewModel
import com.senac.boaviagem.viewmodels.UsuarioViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            BoaViagemTheme {
                val navController = rememberNavController()
                val ctx = LocalContext.current
                val db = AppDatabase.getDatabase(ctx)
                val usuarioViewModel: UsuarioViewModel = viewModel(
                    factory = UsuarioViewModelFactory(db)
                )
                val state = usuarioViewModel.uiState.collectAsState()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    NavHost(navController = navController, startDestination = "login") {
                        composable("login") { LoginScreen(state, usuarioViewModel, ctx) }
                        composable("register") { TelaDeRegistro(state, usuarioViewModel, ctx) }
                        composable("consulta_viagem") { ConsultaViagem(ViagemViewModel()) }
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

//@Composable
//fun LoginScreen(navController: State<Usuario>, usuarioViewModel: UsuarioViewModel, ctx: Context) {
//    LoginScreenContent(navController)
//}
//
//@Composable
//fun LoginScreenContent(navController: State<Usuario>) {
//    Column(
//        modifier = Modifier
//            .fillMaxHeight()
//            .padding(horizontal = 20.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.viagem),
//            contentDescription = "Logo do Login",
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Text("Logar", style = MaterialTheme.typography.headlineMedium)
//
//        val username = remember { mutableStateOf("") }
//        val password = remember { mutableStateOf("") }
//
//        OutlinedTextField(
//            value = username.value,
//            onValueChange = { username.value = it },
//            label = { Text("Usuário") },
//            modifier = Modifier.padding(vertical = 8.dp)
//        )
//        OutlinedTextField(
//            value = password.value,
//            onValueChange = { password.value = it },
//            label = { Text("Senha") },
//            visualTransformation = PasswordVisualTransformation(),
//            modifier = Modifier.padding(vertical = 8.dp)
//        )
//
//        Button(
//            onClick = {
//                navController.navigate("userActivity/${username.value}")
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 8.dp),
//            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA726)) // Laranja claro
//        ) {
//            Text("Logar")
//        }
//
//        Button(
//            onClick = { navController.navigate("register") },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 8.dp),
//            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF35007C))
//        ) {
//            Text("Registrar")
//        }
//    }
//}


@Composable
fun UserActivityScreen(username: String) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        MyApp()
        Text(text = "$username", style = MaterialTheme.typography.headlineSmall)
    }
}

