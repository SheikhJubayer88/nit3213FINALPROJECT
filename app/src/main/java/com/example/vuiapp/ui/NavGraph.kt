package com.example.vuiapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vuiapp.ui.dashboard.DashboardScreen
import com.example.vuiapp.ui.login.LoginScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(onNavigateToDashboard = { keypass ->
                // Encode keypass to make it URL-safe
                val encodedKeypass = java.net.URLEncoder.encode(keypass, "UTF-8")
                navController.navigate("dashboard/$encodedKeypass")
            })
        }

        composable("dashboard/{keypass}") { backStackEntry ->
            // Decode the keypass argument
            val keypass = backStackEntry.arguments?.getString("keypass") ?: ""
            DashboardScreen(keypass = keypass, onNavigateToDetails = { entity ->
                navController.currentBackStackEntry?.arguments?.putParcelable("entity", entity)
                navController.navigate("details")
            })
        }
    }
}