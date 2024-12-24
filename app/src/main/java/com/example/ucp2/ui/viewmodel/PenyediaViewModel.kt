package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.FndApp


object PenyediaViewModel {
    val factory = viewModelFactory {
        initializer {
            DokterViewModel(
                FndApp().dokterApp.repositoryDokter
            )
        }

        initializer {
            HomeDokterViewModel(
                FndApp().dokterApp.repositoryDokter
            )
        }

        initializer {
            JadwalViewModel(
                FndApp().jadwalApp.repositoryJadwal
            )
        }

        initializer {
            HomeJadwalViewModel(
                FndApp().jadwalApp.repositoryJadwal
            )
        }

        initializer {
            DetailJadwalViewModel(
                createSavedStateHandle(),
                FndApp().jadwalApp.repositoryJadwal
            )
        }

        initializer {
            UpdateJadwalViewModel(
                createSavedStateHandle(),
                FndApp().jadwalApp.repositoryJadwal
            )
        }
    }
}

fun CreationExtras.FndApp(): FndApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]as FndApp)