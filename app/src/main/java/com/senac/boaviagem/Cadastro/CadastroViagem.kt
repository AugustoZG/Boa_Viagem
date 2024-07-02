package com.senac.boaviagem.Cadastro

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType

import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.senac.boaviagem.Classes.ViagemViewModel
import androidx.compose.material.Text as Text1


@Composable
fun CadastroViagemContent(navController: NavController, viewModel: ViagemViewModel) {
    val context = LocalContext.current

    var destino by remember { mutableStateOf("") }
    var tipoViagem by remember { mutableStateOf("") }
    var dataInicial by remember { mutableStateOf("") }
    var dataFinal by remember { mutableStateOf("") }
    var orcamento by remember { mutableStateOf("") }

    var viagensSalvas = viewModel.getViagensSalvas()

    var isDialogOpen by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text1(
            text = "Cadastro de Viagem",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        OutlinedTextField(
            value = destino,
            onValueChange = { destino = it },
            label = { Text1("Destino") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = tipoViagem,
            onValueChange = { tipoViagem = it },
            label = { Text1("Tipo de viagem") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = dataInicial,
            onValueChange = { dataInicial = it },
            label = { Text1("Data inicial") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = dataFinal,
            onValueChange = { dataFinal = it },
            label = { Text1("Data final") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = orcamento,
            onValueChange = { orcamento = it },
            label = { Text1("Orçamento") },
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
            Text1("Salvar")
        }
        Button(
            onClick = {
                isDialogOpen = true
            }
        ) {
            Text1("Consultar Viagens Salvas")
        }
    }

    if (isDialogOpen) {
        AlertDialog(
            onDismissRequest = { isDialogOpen = false },
            title = { Text1("Viagens Salvas") },
            text = {
                Column {
                    viagensSalvas.forEach { viagem ->
                        Text1("--------------------------")
                        Text1("Destino: ${viagem.destino}")
                        Text1("Tipo de Viagem: ${viagem.tipoViagem}")
                        Text1("Data Inicial: ${viagem.dataInicial}")
                        Text1("Data Final: ${viagem.dataFinal}")
                        Text1("Orçamento: R$${viagem.orcamento}")
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            },
            confirmButton = {
                Button(onClick = { isDialogOpen = false }) {
                    Text1("Fechar")
                }
            }
        )
    }
}