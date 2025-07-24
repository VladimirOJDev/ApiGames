package com.example.apigames.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.apigames.ui.navigation.AppNavGraph

@Composable
fun GameHomeScreen(
    contentType: GameContentType,
    navController: NavHostController
){
    AppNavGraph(
        navController = navController,
        contentType = contentType
    )
}