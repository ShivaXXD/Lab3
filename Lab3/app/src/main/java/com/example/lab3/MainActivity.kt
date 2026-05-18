package com.example.lab3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel: ProjectViewModel = viewModel()

            NavHost(navController = navController, startDestination = "list") {
                composable("list") {
                    ProjectsListScreen(navController, viewModel)
                }
                composable("add") {
                    AddProjectScreen(navController, viewModel)
                }
                composable(
                    route = "details/{projectId}",
                    arguments = listOf(navArgument("projectId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val projectId = backStackEntry.arguments?.getString("projectId")
                    DetailsProjectScreen(navController, viewModel, projectId)
                }
                composable(
                    route = "edit/{projectId}",
                    arguments = listOf(navArgument("projectId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val projectId = backStackEntry.arguments?.getString("projectId")
                    EditProjectScreen(navController, viewModel, projectId)
                }
            }
        }
    }
}