package com.example.ucp2.ui.view.jadwal


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.Data.entity.Jadwal
import com.example.ucp2.ui.customwidget.TopAppBar
import com.example.ucp2.ui.viewmodel.HomeJadwalUiState
import com.example.ucp2.ui.viewmodel.HomeJadwalViewModel
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardJadwal(
    jwl: Jadwal,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Person Icon",
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = jwl.namaPasien,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = "Date Icon",
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = jwl.namaDokter,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Home Icon",
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = jwl.tanggalKonsultasi,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }
    }
}


@Composable
fun ListJadwal(
    listjwl: List<Jadwal>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = { }
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = listjwl,
            itemContent = { jwl ->
                CardJadwal(
                    jwl = jwl,
                    onClick = {
                        println("Clicked on Jadwal ID: ${jwl.id}")
                        onClick(jwl.id)
                    }
                )
            }
        )
    }
}


@Composable
fun BodyHomeJadwalView(
    homeJadwalUiState: HomeJadwalUiState,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = { }
) {
    val coroutineScope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    when {
        homeJadwalUiState.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        homeJadwalUiState.isError -> {
            LaunchedEffect(homeJadwalUiState.errorMessages) {
                homeJadwalUiState.errorMessages?.let { message ->
                    coroutineScope.launch {
                        snackBarHostState.showSnackbar(message)
                    }
                }
            }
        }

        homeJadwalUiState.listJwl.isEmpty() -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tidak Ada Data Jadwal",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        else -> {
            ListJadwal(
                listjwl = homeJadwalUiState.listJwl,
                onClick = {
                    onClick(it)
                    println("Selected Jadwal ID: $it")
                },
                modifier = modifier
            )
        }
    }
}

@Composable
fun HomeJadwalView(
    viewModel: HomeJadwalViewModel = viewModel(factory = PenyediaViewModel.factory),
    onAddjadwal: () -> Unit = {},
    onDetailClick: (String) -> Unit = {},
    onBack: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                judul = "Daftar Jadwal",
                showBackButton = true,
                onBack = onBack,
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddjadwal,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .padding(16.dp)
                    .clip(CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah Jadwal",
                    tint = Color.White
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        val homeJadwalUiState by viewModel.homeJadwalUiState.collectAsState()

        BodyHomeJadwalView(
            homeJadwalUiState = homeJadwalUiState,
            onClick = {
                onDetailClick(it)
            },
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}