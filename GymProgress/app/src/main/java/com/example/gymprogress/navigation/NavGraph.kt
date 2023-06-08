package com.example.gymprogress.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gymprogress.feature.choose_exercise_screen.ChooseExerciseScreen
import com.example.gymprogress.feature.history_screen.HistoryScreen
import com.example.gymprogress.feature.session_screen.SessionScreen
import com.example.gymprogress.feature.statistic_screen.StatisticScreen

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

                },
                onStatClick = {
                    navController.navigate(Screen.Statistic.route)
                }
            )
        }
        composable(Screen.Session.route,
            arguments = listOf(
                navArgument("sessionId") {
                    type = NavType.IntType
                }
            ))
        {   backStackEntry ->
            val sessionId = backStackEntry.arguments?.getInt("sessionId") ?: -1
            Log.d("MY-NAVGRAPH-SESSION", sessionId.toString())
            SessionScreen(
                sessionId =sessionId,
                onFinished = { navController.navigate(Screen.SessionList.route) },
                onAddExercise = { navController.navigate(Screen.ExerciseTypes.createRoute(sessionId)) },
                onAddNewExerciseType = {  },
                onListItemClick = { },
                onNavigateBack = { navController.popBackStack()
                }
            )
        }
        composable(Screen.ExerciseTypes.route,
                arguments = listOf(
                navArgument("sessionId") {
                    type = NavType.IntType
                }
                )){
                backStackEntry ->
            val sessionId = backStackEntry.arguments?.getInt("sessionId") ?: -1
            Log.d("MY-NAVGRAPH", sessionId.toString())
            ChooseExerciseScreen(
                sessionId = sessionId,
                onExerciseTypeClick = { navController.popBackStack() }
            )
        }
        composable(Screen.Statistic.route
        ){
            StatisticScreen(onNavigateBack = { navController.popBackStack() })
        }
    }
}