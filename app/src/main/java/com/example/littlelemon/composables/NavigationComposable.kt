package com.example.littlelemon.composables

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.littlelemon.navigation.Home
import com.example.littlelemon.navigation.Onboarding
import com.example.littlelemon.navigation.Profile
import android.content.Context

@Composable
fun NavigationComposable(navController: NavHostController, context: Context) {

    val sharedPreferences = context.getSharedPreferences("Little Lemon", Context.MODE_PRIVATE)
    var startDestination = Onboarding.route

    if (sharedPreferences.getBoolean("userRegistered", false)) {
        startDestination = Home.route
    }

    NavHost(navController = navController, startDestination = startDestination){
        composable(Onboarding.route){
            Onboarding(navController, context)
        }
        composable(Home.route){
            Home(navController)
        }
        composable(Profile.route){
            Profile(navController, context)
        }
    }
}