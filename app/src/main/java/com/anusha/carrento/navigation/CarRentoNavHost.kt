package com.anusha.carrento.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.anusha.carrento.screens.carrentalscreen.CarRentalScreen
import com.anusha.carrento.screens.splashscreen.CarRentalSplashScreen

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun CarRentNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            CarRentalSplashScreen(navigateToHomeScreen = {
                navController.navigate("CarRentalScreen") {
                    popUpTo("splash") { inclusive = true }
                }
            })
        }
        composable("CarRentalScreen") {
            CarRentalScreen()
        }
    }

}


