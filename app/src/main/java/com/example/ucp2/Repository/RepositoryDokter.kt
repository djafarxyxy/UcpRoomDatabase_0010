package com.example.ucp2.Repository

import com.example.ucp2.Data.entity.Dokter
import kotlinx.coroutines.flow.Flow

interface RepositoryDokter {
    suspend fun insertDokter(dokter: Dokter)

    fun getAllDokter(): Flow<List<Dokter>>
}