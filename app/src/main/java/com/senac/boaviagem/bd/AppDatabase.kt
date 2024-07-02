package com.senac.boaviagem.bd

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.senac.boaviagem.dao.ViagemDao
import com.senac.boaviagem.entities.Viagem
import androidx.room.Database
import androidx.room.TypeConverters
import com.senac.boaviagem.dao.UsuarioDao
import com.senac.boaviagem.entities.Usuario
import com.senac.boaviagem.utls.Converters
import kotlin.jvm.Volatile

@TypeConverters(Converters::class)
@Database(entities = [Viagem::class,Usuario::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val viagemDao: ViagemDao
    abstract val usuarioDao: UsuarioDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase = INSTANCE ?: synchronized(this){
            val instance = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "viagem-db"
            ).build()
            INSTANCE = instance
            instance
        }
    }

}