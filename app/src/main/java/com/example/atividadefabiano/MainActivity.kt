package com.example.atividadefabiano

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ScreenAtividade()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenAtividade(viewModel: FormularioViewModel = viewModel()) {
    var mostrarDatePicker by remember { mutableStateOf(false) }
    var mostrarTimePicker by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(text = "Agendamento", style = MaterialTheme.typography.headlineLarge)

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Data formatada: ${viewModel.dataSelecionada}")
                Text("Hora formatada: ${viewModel.horaSelecionada}")
            }
        }

        Button(onClick = { mostrarDatePicker = true }, modifier = Modifier.fillMaxWidth()) {
            Text("Selecionar Data")
        }

        Button(onClick = { mostrarTimePicker = true }, modifier = Modifier.fillMaxWidth()) {
            Text("Selecionar Hora")
        }
    }

    // Componente de Data
    if (mostrarDatePicker) {
        val datePickerState = rememberDatePickerState()
        DatePickerDialog(
            onDismissRequest = { mostrarDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.atualizarData(datePickerState.selectedDateMillis)
                    mostrarDatePicker = false
                }) { Text("Confirmar") }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    // Componente de Hora
    if (mostrarTimePicker) {
        val timePickerState = rememberTimePickerState()
        AlertDialog(
            onDismissRequest = { mostrarTimePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.atualizarHora(timePickerState.hour, timePickerState.minute)
                    mostrarTimePicker = false
                }) { Text("Confirmar") }
            },
            text = { TimePicker(state = timePickerState) }
        )
    }
}