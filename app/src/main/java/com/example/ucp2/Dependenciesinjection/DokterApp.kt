package com.example.ucp2.Dependenciesinjection

import android.content.Context
import com.example.ucp2.Data.database.Databasefnd
import com.example.ucp2.Repository.LocalRepositoryDokter
import com.example.ucp2.Repository.RepositoryDokter

interface InterfaceDokterApp {
    val repositoryDokter: RepositoryDokter
}

class DokterApp(private val context: Context) : InterfaceDokterApp {
    override val repositoryDokter: RepositoryDokter by lazy {
        LocalRepositoryDokter(Databasefnd.getDatabase(context).DokterDao())
    }
}