package com.senac.boaviagem.Consulta

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.senac.boaviagem.Classes.Produto
import com.senac.boaviagem.ui.theme.BoaViagemTheme
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold


class ConsultaViagems : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoaViagemTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ConsultaViagem()
                }
            }
        }
    }
}
@Composable
fun ConsultaViagem() {
    val list = listOf(
        Produto(1,"caneta",10.0),
        Produto(2,"tenis",2.0)
    )
    Scaffold {
        Column (modifier = Modifier.padding(it)){
            LazyColumn(){
                item { Text(text = "Line 1") }
                item { Text(text = "Line 2") }
                items(5) {
                    Text(text = "Line ${it}")
                }
                items(list){
                    ProdutoCard(it)
                }

            }
        }
    }
}
@Composable
fun ProdutoCard(p: Produto){
    val ctx = LocalContext.current
    Card(elevation = CardDefaults.cardElevation(
        defaultElevation = 4.dp
    ),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable {
                Toast
                    .makeText(ctx, "Produto: ${p.nome}", Toast.LENGTH_SHORT)
                    .show()
            }
    ) {
        Column(modifier = Modifier.padding(6.dp)) {
            Text(text = p.nome)
            Text(text = "R$  ${p.preco}")

        }

   }
}


@Preview(showBackground = true)
@Composable
fun PreviewScaffold() {
    BoaViagemTheme() {
        ConsultaViagem()
    }
}