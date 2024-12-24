package com.example.ucp2.Data.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ucp2.Data.entity.Dokter
import kotlinx.coroutines.flow.Flow

@Dao
interface DokterDao {
    @Insert
    suspend fun insertDokter(dokter: Dokter)

    @Query("SELECT * FROM Dokter ORDER BY nama ASC")
    fun getAllDokter():Flow<List<Dokter>>
}