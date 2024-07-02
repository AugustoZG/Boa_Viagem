package com.senac.boaviagem.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.senac.boaviagem.entities.Viagem
import kotlinx.coroutines.flow.Flow

@Dao
interface ViagemDao {
    @Insert
    fun insert(viagem: Viagem): Long
    @Update
    fun update(viagem: Viagem)
    @Upsert
    suspend fun upsert(viagem: Viagem): Long
    @Query("select * from viagem p order by p.tipoviagem")
    fun getAll(): Flow<List<Viagem>>
    @Query("select * from viagem p where p.id = :id")
    fun findById(id: Long) : Viagem?
    @Delete
    fun delete(viagem: Viagem)

}