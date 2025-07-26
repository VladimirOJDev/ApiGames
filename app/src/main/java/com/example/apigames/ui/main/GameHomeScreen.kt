package com.example.apigames.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.apigames.ui.navigation.AppNavGraph
import com.example.apigames.ui.screens.homeScreen.HomeViewModel

@Composable
fun GameHomeScreen(
    viewModel: HomeViewModel,
    contentType: GameContentType,
    navController: NavHostController
){
    AppNavGraph(
        viewModel,
        navController = navController,
        contentType = contentType
    )
}