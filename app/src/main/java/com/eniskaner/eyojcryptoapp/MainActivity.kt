package com.eniskaner.eyojcryptoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.eniskaner.eyojcryptoapp.ui.theme.EyojCryptoAppTheme
import com.eniskaner.eyojcryptoapp.view.CyrptoDetailScreen
import com.eniskaner.eyojcryptoapp.view.CyrptoListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EyojCryptoAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "cyrpto_list_screen") {
                    composable("cyrpto_list_screen") {
                        //CyrptoListScreen
                        CyrptoListScreen(navController = navController)
                    }
                    composable("cyrpto_detail_screen/{cyrptoId}/{cyrptoPrice}", arguments = listOf(
                        navArgument("cyrptoId") {
                            type = NavType.StringType
                        },
                        navArgument("cyrptoPrice") {
                            type = NavType.StringType
                        }
                    )) {
                        val cyrptoId = remember {
                            it.arguments?.getString("cyrptoId")
                        }
                        val cyrptoPrice = remember {
                            it.arguments?.getString("cyrptoPrice")
                        }
                        //CyrptoDetailScreen
                        CyrptoDetailScreen(
                            id = cyrptoId ?: "",
                            price = cyrptoPrice ?: "",
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}
