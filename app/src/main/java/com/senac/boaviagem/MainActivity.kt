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
                        composable("login") { LoginScreen(navController,state, usuarioViewModel, ctx) }
                        composable("register") { TelaDeRegistro(navController,state, usuarioViewModel, ctx) }
                        composable("logado"){UserActivityScreen(state.value.usuario)}
                        composable("consulta_viagem") { ConsultaViagem(ViagemViewModel()) }
                        composable("userActivity/{username}") { backStackEntry ->
                            val username = backStackEntry.arguments?.getString("username") ?: "Desconhecido"

                        }
                    }
                }
            }
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

