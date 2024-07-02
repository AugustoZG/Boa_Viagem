package com.senac.boaviagem.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.senac.boaviagem.bd.AppDatabase
import com.senac.boaviagem.dao.UsuarioDao
import com.senac.boaviagem.entities.Usuario
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

class UsuarioViewModelFactory(val db: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return UsuarioViewModel(db.usuarioDao) as T
    }
}

class UsuarioViewModel(val usuariodao: UsuarioDao): ViewModel() {

    private val _uiState = MutableStateFlow(Usuario())
    val uiState: StateFlow<Usuario> = _uiState.asStateFlow()
    fun updateUsuario(usuario: String) {
        _uiState.update {
            it.copy(usuario = usuario)
        }
    }
    fun updateEmail(email: String) {
        _uiState.update {
            it.copy(email = email)
        }
    }
    fun updateSenha(senha: String) {
        _uiState.update {
            it.copy(senha = senha)
        }
    }
    private fun updateId(id: Long) {
        _uiState.update {
            it.copy(id = id)
        }
    }
    private fun new() {
        _uiState.update {
            it.copy(id = 0, usuario = "", senha = "", email = "")
        }
    }
    fun save() {
        viewModelScope.launch {
            val id = usuariodao.upsert(uiState.value)
            if (id > 0) {
                updateId(id)
            }
        }
    }
    fun saveNew() {
        save()
        new()
    }
    fun getAll() = usuariodao.getAll()

    suspend fun login(email: String, senha: String) : Usuario? {
        val deferred: Deferred<Usuario?> = viewModelScope.async {
            usuariodao.login(email, senha)
        }
        return deferred.await()
    }
}