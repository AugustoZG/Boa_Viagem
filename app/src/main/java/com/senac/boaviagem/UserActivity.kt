package com.senac.boaviagem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.senac.boaviagem.ui.theme.BoaViagemTheme

class UserActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoaViagemTheme {
                val username = intent.getStringExtra("username") ?: "Usuário Desconhecido"
                UserGreeting(username)
            }
        }
    }
}

@Composable
fun UserGreeting(name: String) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Text(text = "Bem-vindo, $name!", style = MaterialTheme.typography.headlineMedium)
    }
}