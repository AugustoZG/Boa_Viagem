package com.senac.boaviagem.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.senac.boaviagem.entities.Usuario
import com.senac.boaviagem.entities.Viagem
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {
    @Insert
    fun insert(usuario: Usuario): Long
    @Update
    fun update(usuario: Usuario)
    @Upsert
    suspend fun upsert(usuario: Usuario): Long
    @Query("select * from usuario p order by p.usuario")
    fun getAll(): Flow<List<Usuario>>
    @Query("select * from usuario u where u.usuario = :usuario and u.senha = :senha")
    suspend fun login(usuario: String, senha: String) : Usuario?
    @Query("select * from usuario p where p.id = :id")
    fun findById(id: Long) : Usuario?
    @Delete
    fun delete(usuario: Usuario)

}