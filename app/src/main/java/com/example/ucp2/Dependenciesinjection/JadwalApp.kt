package com.example.ucp2.Dependenciesinjection

import android.content.Context
import com.example.ucp2.Data.database.Databasefnd
import com.example.ucp2.Repository.LocalRepositoryJadwal
import com.example.ucp2.Repository.RepositoryJadwal

interface InterfaceJadwalApp {
    val repositoryJadwal: RepositoryJadwal
}

class JadwalApp(private val context: Context) : InterfaceJadwalApp {
    override val repositoryJadwal: RepositoryJadwal by lazy {
        LocalRepositoryJadwal(Databasefnd.getDatabase(context).JadwalDao())
    }
}