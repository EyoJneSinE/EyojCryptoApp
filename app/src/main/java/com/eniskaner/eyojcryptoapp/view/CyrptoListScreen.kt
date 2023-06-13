package com.eniskaner.eyojcryptoapp.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.eniskaner.eyojcryptoapp.viewmodel.CyrptoListViewModel

@Composable
fun CyrptoListScreen(
    navController: NavController,
    viewModel: CyrptoListViewModel = hiltViewModel()
) {
    Surface(
        color = MaterialTheme.colorScheme.secondary,
        modifier = Modifier.fillMaxSize()
    ) {

    }
}