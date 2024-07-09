package com.senac.boaviagem.Cadastro

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.senac.boaviagem.R
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import androidx.compose.material.Text as Text1


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadastroViagemContent(navController: NavController, viewModel: com.senac.boaviagem.viewmodels.ViagemViewModel) {
    val context = LocalContext.current

    var destino by remember { mutableStateOf("") }
    var tipoViagem by remember { mutableStateOf("") }
    var dataInicial by remember { mutableStateOf("") }
    val datePickerStateIni = rememberDatePickerState()
    var dataFinal by remember { mutableStateOf("") }
    val datePickerStateFin = rememberDatePickerState()
    var orcamento by remember { mutableStateOf("") }


    val focusManager = LocalFocusManager.current
    var showDatePickerDialog by remember {
        mutableStateOf(false)
    }
    var showDatePickerDialog2 by remember {
        mutableStateOf(false)
    }

    if (showDatePickerDialog) {
        DatePickerDialog(
            onDismissRequest = { showDatePickerDialog = false },
            confirmButton = {
                Button(
                    onClick = {
                        datePickerStateFin
                            .selectedDateMillis?.let { millis ->
                                dataFinal = SimpleDateFormat("dd/MM/yyyy").format(millis)
                            }
                        val instant =
                            Instant.ofEpochMilli(datePickerStateFin.selectedDateMillis ?: 0)
                        val date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
                        viewModel.updateDataFinal(date.toLocalDate())
                        showDatePickerDialog = false
                    }) {
                    Text(text = "Escolher data")
                }
                DatePicker(state = datePickerStateFin)
            }) {
        }
    }

    if (showDatePickerDialog2) {
        DatePickerDialog(
            onDismissRequest = { showDatePickerDialog = false },
            confirmButton = {
                Button(
                    onClick = {
                        datePickerStateIni
                            .selectedDateMillis?.let { millis ->
                                dataInicial = SimpleDateFormat("dd/MM/yyyy").format(millis)
                            }

                        val instant =
                            Instant.ofEpochMilli(datePickerStateIni.selectedDateMillis ?: 0)
                        val date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
                        viewModel.updateDatainicial(date.toLocalDate())
                        showDatePickerDialog2 = false
                    }) {
                    Text(text = "Escolher data")
                }
                DatePicker(state = datePickerStateIni)
            }) {
        }
    }

    var isDialogOpen by remember { mutableStateOf(false) }

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
            verticalArrangement = Arrangement.Center

        )
        {
            Text1(
                text = "Cadastro de Viagem",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 30.sp
                ),
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
                readOnly = true,
                value = dataInicial,
                onValueChange = { dataInicial = it },
                label = { Text("Data Inicial") },
                modifier = Modifier.onFocusEvent {
                    if (it.isFocused) {
                        showDatePickerDialog2 = true
                        focusManager.clearFocus(force = true)
                    }
                },
            )
            OutlinedTextField(
                readOnly = true,
                value = dataFinal,
                onValueChange = { dataFinal = it },
                label = { Text("Data Final") },
                modifier = Modifier.onFocusEvent {
                    if (it.isFocused) {
                        showDatePickerDialog = true
                        focusManager.clearFocus(force = true)
                    }
                },
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
                    if (orcamento.isNotBlank() && destino.isNotBlank() && tipoViagem.isNotBlank()) {
                        viewModel.updatetipoViagem(tipoViagem)
                        viewModel.updateDestino(destino)
                        viewModel.updateOrcamento(orcamento.toDouble())
                        viewModel.saveNew()
                        Toast.makeText(context, "Viagem salva", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text1("Salvar")
            }
            Button(
                onClick = {
                    navController.navigate("consulta_viagem")
                }
            ) {
                Text1("Consultar Viagens Salvas")
            }
        }
    }
}