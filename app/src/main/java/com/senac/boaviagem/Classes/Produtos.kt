package com.senac.boaviagem.Classes

data class Produto(
    val id: Int = 0,
    val nome: String = "",
    val preco: Double = 0.0)
data class Viagem(
    val destino: String,
    val tipoViagem: String,
    val dataInicial: String,
    val dataFinal: String,
    val orcamento: Double
)

