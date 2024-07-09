package com.senac.boaviagem.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.senac.boaviagem.bd.AppDatabase
import com.senac.boaviagem.dao.ViagemDao
import com.senac.boaviagem.entities.Viagem
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

class ViagemViewModelFactory(val db: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return ViagemViewModel(db.viagemDao) as T
    }
}

class ViagemViewModel(val viagemdao: ViagemDao): ViewModel() {

    private val _uiState = MutableStateFlow(Viagem())
    val uiState: StateFlow<Viagem> = _uiState.asStateFlow()
    fun updatetipoViagem(tipoViagem: String) {
        _uiState.update {
            it.copy(tipoviagem = tipoViagem)
        }
    }
    fun updateDestino(Destino: String) {
        _uiState.update {
            it.copy(destino = Destino)
        }
    }
    fun updateDatainicial(dataInicial: LocalDate) {
        _uiState.update {
            it.copy(dataInicial = dataInicial)
        }
    }
    fun updateDataFinal(dataFinal: LocalDate) {
        _uiState.update {
            it.copy(dataFinal = dataFinal)
        }
    }
    fun updateOrcamento(orcamento: Double) {
        _uiState.update {
            it.copy(orcamento = orcamento)
        }
    }
    private fun updateId(id: Long) {
        _uiState.update {
            it.copy(id = id)
        }
    }

    suspend fun delete(viagem: Viagem){
        viagemdao.delete(viagem)
    }
    private fun new() {
        _uiState.update {
            val copy = it.copy(
                id = 0,
                tipoviagem = "",
                destino = "",
                dataInicial = LocalDate.now(),
                dataFinal = LocalDate.now(),
                orcamento = 0.0
            )
            copy
        }
    }
    fun save() {
        viewModelScope.launch {
            val id = viagemdao.upsert(uiState.value)
            if (id > 0) {
                updateId(id)
            }
        }
    }

    suspend fun findById(id: Long): Viagem?{
        val deferred: Deferred<Viagem?> = viewModelScope.async {
            viagemdao.findById(id)
        }
        return deferred.await()
    }
    fun saveNew() {
        save()
        new()
    }
    fun getAll() = viagemdao.getAll()
}