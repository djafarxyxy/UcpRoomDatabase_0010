package com.example.ucp2.Repository

import com.example.ucp2.Data.Dao.DokterDao
import com.example.ucp2.Data.entity.Dokter
import kotlinx.coroutines.flow.Flow

class LocalRepositoryDokter(
    private val dokterDao: DokterDao
) : RepositoryDokter {
    override suspend fun insertDokter(dokter: Dokter) {
        dokterDao.insertDokter(dokter)
    }

    override fun getAllDokter(): Flow<List<Dokter>> {
        return dokterDao.getAllDokter()
    }
}