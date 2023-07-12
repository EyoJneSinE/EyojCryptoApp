package com.eniskaner.eyojcryptoapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.eniskaner.eyojcryptoapp.model.Crypto
import com.eniskaner.eyojcryptoapp.util.Resource
import com.eniskaner.eyojcryptoapp.viewmodel.CryptoDetailViewModel

@Composable
fun CryptoDetailScreen(
    id: String,
    price: String,
    navController: NavController,
    viewModel: CryptoDetailViewModel = hiltViewModel()
) {
    val cryptoItemState = remember { mutableStateOf<Resource<Crypto>>(Resource.Loading())}
    
    LaunchedEffect(id) {
        val crypto = viewModel.getCyrpto(id)
        cryptoItemState.value = crypto
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.secondary),
        contentAlignment = Alignment.Center,
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            item {
                when (val resource = cryptoItemState.value) {
                    is Resource.Success -> {
                        val selectedCrypto = resource.data
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = selectedCrypto!!.name,
                                style = MaterialTheme.typography.h3,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colors.primary,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Image(
                                painter = rememberImagePainter(data = selectedCrypto.logo),
                                contentDescription = selectedCrypto.name,
                                modifier = Modifier
                                    .padding(vertical = 16.dp)
                                    .size(150.dp)
                                    .clip(CircleShape)
                                    .border(2.dp, Color.Gray, CircleShape),
                                alignment = Alignment.Center
                            )
                            Text(
                                text = price,
                                style = MaterialTheme.typography.h4,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colors.primaryVariant,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Text(
                                text = selectedCrypto.description,
                                style = MaterialTheme.typography.h4,
                                color = MaterialTheme.colors.primary,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                    is Resource.Error -> {
                        Text(
                            text = resource.message!!,
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.error,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    is Resource.Loading -> {
                        CircularProgressIndicator(
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier.wrapContentSize(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}