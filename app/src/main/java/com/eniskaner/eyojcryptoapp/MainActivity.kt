package com.eniskaner.eyojcryptoapp

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.eniskaner.eyojcryptoapp.ui.theme.EyojCryptoAppTheme
import com.eniskaner.eyojcryptoapp.view.CryptoDetailScreen
import com.eniskaner.eyojcryptoapp.view.CyrptoListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR
        setContent {
            EyojCryptoAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "crypto_list_screen"
                ) {
                    composable("crypto_list_screen") {
                        CyrptoListScreen(navController = navController)
                    }
                    composable(
                        "crypto_detail_screen/{cryptoId}/{cryptoPrice}",
                        arguments = listOf(
                            navArgument("cryptoId") {
                                type = NavType.StringType
                            },
                            navArgument("cryptoPrice") {
                                type = NavType.StringType
                            }
                        )
                    ) {
                        val cryptoId = remember {
                            it.arguments?.getString("cryptoId")
                        }
                        val cryptoPrice = remember {
                            it.arguments?.getString("cryptoPrice")
                        }
                        CryptoDetailScreen(
                            id = cryptoId ?: "",
                            price = cryptoPrice ?: "",
                            navController = navController
                        )
                    }
                }
            }
        }



    }
}
