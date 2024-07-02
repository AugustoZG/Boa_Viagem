package com.senac.boaviagem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.senac.boaviagem.Cadastro.CadastroViagemContent
import com.senac.boaviagem.Classes.ViagemViewModel
import com.senac.boaviagem.Consulta.ConsultaViagem
import com.senac.boaviagem.ui.theme.BoaViagemTheme
import com.senac.boaviagem.HomePage


class TaskBar : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoaViagemTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}


private fun isSelected(currentDestination: NavDestination?, route: String): Boolean {
    return currentDestination?.hierarchy?.any { it.route == route } == true
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            val navBackStackEntry = navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry.value?.destination
            BottomNavigation {
                BottomNavigationItem(
                    selected = isSelected(currentDestination, "profile"),
                    onClick = { navController.navigate("profile") },
                    icon = {
                        Icon(imageVector = Icons.Filled.AccountBox, contentDescription = "")
                    })

                BottomNavigationItem(
                    selected = isSelected(currentDestination, "viagem"),
                    onClick = { navController.navigate("viagem") },
                    icon = {
                        Icon(imageVector = Icons.Filled.AddCircle, contentDescription = "")
                    })
                BottomNavigationItem(
                    selected = isSelected(currentDestination, "consulta_viagem"),
                    onClick = { navController.navigate("consulta_viagem") },
                    icon = {
                        Icon(imageVector = Icons.Outlined.Edit, contentDescription = "")
                    })

                BottomNavigationItem(
                    selected = isSelected(currentDestination, "about"),
                    onClick = { navController.navigate("about") },
                    icon = {
                        Icon(imageVector = Icons.Outlined.Info, contentDescription = "")
                    })
            }
        }
    )
    {
        val viewModel = ViagemViewModel()
        Column(modifier = Modifier.padding(it)) {
            NavHost(navController = navController, startDestination = "profile") {
                composable("profile") {
                    HomePageContent()
                }
                composable("about") {
                    Tela03()
                }
                composable("consulta_viagem") {
                    ConsultaViagem(viewModel)
                }
                composable("viagem") {
                    CadastroViagemContent(navController, viewModel)
                }
            }
        }
    }
}

@Composable
fun Tela02() {}

@Composable
fun Tela03() {}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BoaViagemTheme {
        MyApp()
    }
}