package com.example.ucp2.Repository

import com.example.ucp2.Data.entity.Jadwal
import kotlinx.coroutines.flow.Flow

interface RepositoryJadwal {
    suspend fun insertJadwal(jadwal: Jadwal)

    fun getAllJadwal(): Flow<List<Jadwal>>

    fun getJadwal(id: String): Flow<Jadwal>

    suspend fun updateJadwal(jadwal: Jadwal)

    suspend fun deleteJadwal(jadwal: Jadwal)
}