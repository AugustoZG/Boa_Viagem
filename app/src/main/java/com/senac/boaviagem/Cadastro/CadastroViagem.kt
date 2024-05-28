package com.senac.boaviagem.Cadastro

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType

import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


class ViagemViewModel : ViewModel() {
    private val viagensSalvas = mutableListOf<Viagem>()

    fun salvarViagem(destino: String, tipoViagem: String, dataInicial: String, dataFinal: String, orcamento: Double) {
        val viagem = Viagem(destino, tipoViagem, dataInicial, dataFinal, orcamento)
        viagensSalvas.add(viagem)
    }

    fun getViagensSalvas(): List<Viagem> {
        return viagensSalvas.toList()
    }

}

data class Viagem(
    val destino: String,
    val tipoViagem: String,
    val dataInicial: String,
    val dataFinal: String,
    val orcamento: Double
)

@Composable
fun CadastroViagemContent(viewModel: ViagemViewModel = viewModel()) {
    val context = LocalContext.current

    var destino by remember { mutableStateOf("") }
    var tipoViagem by remember { mutableStateOf("") }
    var dataInicial by remember { mutableStateOf("") }
    var dataFinal by remember { mutableStateOf("") }
    var orcamento by remember { mutableStateOf("") }

    val viagensSalvas = viewModel.getViagensSalvas()

    var isDialogOpen by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Cadastro de Viagem",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        OutlinedTextField(
            value = destino,
            onValueChange = { destino = it },
            label = { Text("Destino") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = tipoViagem,
            onValueChange = { tipoViagem = it },
            label = { Text("Tipo de viagem") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = dataInicial,
            onValueChange = { dataInicial = it },
            label = { Text("Data inicial") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = dataFinal,
            onValueChange = { dataFinal = it },
            label = { Text("Data final") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = orcamento,
            onValueChange = { orcamento = it },
            label = { Text("Orçamento") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.salvarViagem(destino, tipoViagem, dataInicial, dataFinal, orcamento.toDouble())
            },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text("Salvar")
        }
        Button(
            onClick = {
                isDialogOpen = true
            }
        ) {
            Text("Consultar Viagens Salvas")
        }
    }

    if (isDialogOpen) {
        AlertDialog(
            onDismissRequest = { isDialogOpen = false },
            title = { Text("Viagens Salvas") },
            text = {
                Column {
                    viagensSalvas.forEach { viagem ->
                        Text("--------------------------")
                        Text("Destino: ${viagem.destino}")
                        Text("Tipo de Viagem: ${viagem.tipoViagem}")
                        Text("Data Inicial: ${viagem.dataInicial}")
                        Text("Data Final: ${viagem.dataFinal}")
                        Text("Orçamento: R$${viagem.orcamento}")
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            },
            confirmButton = {
                Button(onClick = { isDialogOpen = false }) {
                    Text("Fechar")
                }
            }
        )
    }
}