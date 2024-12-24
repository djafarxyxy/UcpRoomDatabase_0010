package com.example.ucp2.ui.Navigation

interface AlamatNavigasi {
    val route: String

    object DestinasiHome : AlamatNavigasi {
        override val route = "home"
    }

    object DestinasiHomeJad: AlamatNavigasi {
        override val route = "Jadwal"
    }

    object DestinasiDetail : AlamatNavigasi {
        override val route = "detail"
        const val id = "Id"
        val routeWithArgs = "$route/{$id}"
    }

    object DestinasiUpdate : AlamatNavigasi {
        override val route = "update"
        const val id = "Id"
        val routeWithAgrs = "$route/{$id}"
    }

    object DestinasiEdit : AlamatNavigasi {
        override val route = "edit"
        const val id = "Id"
        val routeWithArgs = "$route/{$id}"
    }
}