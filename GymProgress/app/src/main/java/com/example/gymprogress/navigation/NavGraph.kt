package com.example.gymprogress.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gymprogress.feature.history_screen.HistoryScreen
import com.example.gymprogress.feature.session_screen.SessionScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SessionList.route
    ) {
        composable(Screen.SessionList.route) {
            HistoryScreen(
                onListItemClick = {
                    navController.navigate(Screen.Session.createRoute(it))
                },
                onFabClick = {
                    //TODO: Navigate to create screen
                }
            )
        }
        composable(Screen.Session.route,
            arguments = listOf(
                navArgument("sessionId") {
                    type = NavType.IntType
                }
            ))
        {
            SessionScreen(
                onFinished = { /*TODO*/ },
                onAddExercise = { /*TODO*/ },
                onAddNewExerciseType = { /*TODO*/ },
                onListItemClick = { },
                onNavigateBack = { navController.popBackStack(
                    route = Screen.SessionList.route,
                    inclusive = true
                )}
            )
        }
    }
}