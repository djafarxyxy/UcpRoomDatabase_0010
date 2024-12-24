package com.example.ucp2.Data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2.Data.Dao.DokterDao
import com.example.ucp2.Data.Dao.JadwalDao
import com.example.ucp2.Data.entity.Dokter
import com.example.ucp2.Data.entity.Jadwal

@Database(entities = [Dokter::class, Jadwal::class], version = 1, exportSchema = false)
abstract class Databasefnd : RoomDatabase(){
    abstract fun DokterDao(): DokterDao
    abstract fun JadwalDao(): JadwalDao

    companion object {
        @Volatile
        private var Instance: Databasefnd? = null

        fun getDatabase(context: Context): Databasefnd {
            return (Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    Databasefnd::class.java,
                    "Databasefnd"
                )
                    .build().also { Instance = it }
            })
        }
    }
}