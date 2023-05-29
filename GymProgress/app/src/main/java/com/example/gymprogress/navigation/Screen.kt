package com.example.gymprogress.navigation

sealed class Screen (val route: String){
    object SessionList : Screen("history_screen")
    object Session : Screen("session/{sessionId}"){
        fun createRoute(sessionId: Int) = "session/$sessionId"
    }

    object ExerciseTypes : Screen("exercise_picker/{sessionId}"){
        fun createRoute(_sessionId: Int) = "exercise_picker/$_sessionId"
    }
}