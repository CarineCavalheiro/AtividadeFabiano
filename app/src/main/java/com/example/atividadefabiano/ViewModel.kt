package com.example.atividadefabiano

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class FormularioViewModel : ViewModel() {
    var dataSelecionada by mutableStateOf("Nenhuma data")
        private set

    var horaSelecionada by mutableStateOf("Nenhuma hora")
        private set

    fun atualizarData(millis: Long?) {
        millis?.let {
            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            // Ajuste para fuso horário local
            val date = Date(it + (1000 * 60 * 60 * 24))
            dataSelecionada = formatter.format(date)
        }
    }

    fun atualizarHora(hora: Int, minuto: Int) {
        horaSelecionada = String.format("%02d:%02d", hora, minuto)
    }
}