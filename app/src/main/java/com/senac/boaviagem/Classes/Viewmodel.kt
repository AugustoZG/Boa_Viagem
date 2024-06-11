package com.senac.boaviagem.Classes

import androidx.lifecycle.ViewModel

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