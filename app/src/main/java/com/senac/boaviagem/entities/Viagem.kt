package com.senac.boaviagem.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Viagem(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val tipoviagem: String = "",
    val destino: String = "",
    val dataFinal: LocalDate = LocalDate.now(),
    val dataInicial: LocalDate = LocalDate.now(),
    val orcamento: Double = 0.0
) {
}