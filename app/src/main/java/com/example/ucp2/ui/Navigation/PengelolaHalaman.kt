package com.example.ucp2.ui.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucp2.ui.view.dokter.DestinasiInsertDokter
import com.example.ucp2.ui.view.dokter.HomeDokterView
import com.example.ucp2.ui.view.dokter.InsertDokterView
import com.example.ucp2.ui.view.jadwal.DestinasiInsertJadwal
import com.example.ucp2.ui.view.jadwal.DetailJadwalView
import com.example.ucp2.ui.view.jadwal.HomeJadwalView
import com.example.ucp2.ui.view.jadwal.InsertJadwalView
import com.example.ucp2.ui.view.jadwal.UpdateJadwalView


@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = AlamatNavigasi.DestinasiHome.route
    ) {
        composable(
            route = AlamatNavigasi.DestinasiHome.route
        ) {
            HomeDokterView(
                onAddDokter = {
                    navController.navigate(DestinasiInsertDokter.route)
                },
                onAddJadwal = {
                    navController.navigate(AlamatNavigasi.DestinasiHomeJad.route)
                },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiInsertDokter.route
        ) {
            InsertDokterView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }

        composable(
            route = AlamatNavigasi.DestinasiHomeJad.route
        ) {
            HomeJadwalView(
                onBack = {
                    navController.popBackStack()
                },
                onDetailClick = { id ->
                    navController.navigate("${AlamatNavigasi.DestinasiDetail.route}/$id")
                    println(
                        "PengelolaHalaman: id = $id"
                    )
                },
                onAddjadwal = {
                    navController.navigate(DestinasiInsertJadwal.route)
                },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiInsertJadwal.route
        ) {
            InsertJadwalView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier,
            )
        }

        composable(
            AlamatNavigasi.DestinasiDetail.routeWithArgs,
            arguments = listOf(
                navArgument(AlamatNavigasi.DestinasiDetail.id) {
                    type = NavType.StringType
                }
            )
        ) {
            val id = it.arguments?.getString(AlamatNavigasi.DestinasiDetail.id)
            id?.let { id ->
                DetailJadwalView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${AlamatNavigasi.DestinasiEdit.route}/$it")
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(
            AlamatNavigasi.DestinasiEdit.routeWithArgs,
            arguments = listOf(
                navArgument(AlamatNavigasi.DestinasiEdit.id) {
                    type = NavType.StringType
                }
            )
        ) {
            UpdateJadwalView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier,
            )
        }
    }
}