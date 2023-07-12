package com.eniskaner.eyojcryptoapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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
fun CyrptoDetailScreen(
    id:String,
    price:String,
    navController: NavController,
    viewModel: CryptoDetailViewModel = hiltViewModel()
) {

    val cryptoItem = produceState<Resource<Crypto>>(initialValue = Resource.Loading()) {
        value = viewModel.getCyrpto(id)
    }.value


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.secondary),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            when (cryptoItem) {
                is Resource.Success -> {
                    val selectedCrypto = cryptoItem.data
                    Text(
                        text = selectedCrypto!!.name,
                        style = MaterialTheme.typography.h3,
                        modifier = Modifier.padding(2.dp),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.primary,
                        textAlign = TextAlign.Center
                    )
                    Image(
                        painter = rememberImagePainter(data = selectedCrypto.logo),
                        contentDescription = selectedCrypto.name,
                        modifier = Modifier
                            .padding(16.dp)
                            .size(200.dp, 200.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.Gray, CircleShape)
                    )
                    Text(
                        text = price,
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.padding(2.dp),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.primaryVariant,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = selectedCrypto.description,
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.padding(2.dp),
                        color = MaterialTheme.colors.primary,
                        textAlign = TextAlign.Start
                    )
                }
                is Resource.Error -> {
                    Text(cryptoItem.message!!)
                }
                is Resource.Loading -> {
                    CircularProgressIndicator(color = MaterialTheme.colors.primary)
                }
            }
        }
    }
}