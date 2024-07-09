package com.senac.boaviagem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.senac.boaviagem.Cadastro.CadastroViagemContent
import com.senac.boaviagem.Classes.ViagemViewModel
import com.senac.boaviagem.Consulta.ConsultaViagem
import com.senac.boaviagem.bd.AppDatabase
import com.senac.boaviagem.ui.theme.BoaViagemTheme
import com.senac.boaviagem.viewmodels.ViagemViewModelFactory


class TaskBar : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoaViagemTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp("", "")
                }
            }
        }
    }
}


private fun isSelected(currentDestination: NavDestination?, route: String): Boolean {
    return currentDestination?.hierarchy?.any { it.route == route } == true
}

@Composable
fun MyApp(username: String, email: String) {
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
        val ctx = LocalContext.current
        val db = AppDatabase.getDatabase(ctx)
        val viagemViewModel: com.senac.boaviagem.viewmodels.ViagemViewModel = viewModel(factory = ViagemViewModelFactory(db))
        Column(modifier = Modifier.padding(it)) {
            NavHost(navController = navController, startDestination = "profile") {
                composable("profile") {
                    HomePageContent()
                }
                composable("about") {
                    UserInfoScreen(username, email)
                }
                composable("consulta_viagem") {
                    ConsultaViagem(viagemViewModel, navController)
                }
                composable("viagem") {
                    CadastroViagemContent(navController, viagemViewModel, )
                }
            }
        }
    }
}

@Composable
fun UserInfoScreen(username: String, email: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.user),
                contentDescription = "User Icon",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.8f))
                    .padding(16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = username,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 24.sp
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = email,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.White,
                    fontSize = 16.sp
                )
            )
        }
    }
}
