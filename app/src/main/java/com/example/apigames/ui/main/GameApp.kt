package com.example.apigames.ui.main

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.apigames.ui.screens.homeScreen.HomeViewModel


@Composable
fun GameApp(
    windowsSize: WindowWidthSizeClass,
    viewModel: HomeViewModel = hiltViewModel()
){
    val navController: NavHostController = rememberNavController()

    val contentType: GameContentType = when(windowsSize){
        WindowWidthSizeClass.Compact -> {
            GameContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Medium-> {
            GameContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Expanded -> {
            GameContentType.LIST_AND_DETAIL
        }
        else -> {
            GameContentType.LIST_ONLY
        }
    }

    GameHomeScreen(
        viewModel,
        contentType,
        navController
    )
}