package com.example.ucp2.Repository

import com.example.ucp2.Data.Dao.JadwalDao
import com.example.ucp2.Data.entity.Jadwal
import kotlinx.coroutines.flow.Flow

class LocalRepositoryJadwal(
    private val jadwalDao: JadwalDao
) : RepositoryJadwal {
    override suspend fun insertJadwal(jadwal: Jadwal) {
        jadwalDao.insertJadwal(jadwal)
    }

    override suspend fun updateJadwal(jadwal: Jadwal) {
        jadwalDao.updateJadwal(jadwal)
    }

    override suspend fun deleteJadwal(jadwal: Jadwal) {
        jadwalDao.deleteJadwal(jadwal)
    }

    override fun getAllJadwal(): Flow<List<Jadwal>> {
        return jadwalDao.getAllJadwal()
    }

    override fun getJadwal(id: String): Flow<Jadwal> {
        return jadwalDao.getAllJadwal(id)
    }
}