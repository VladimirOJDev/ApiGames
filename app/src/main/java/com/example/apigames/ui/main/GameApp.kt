package com.example.apigames.ui.main

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Composable
fun GameApp(
    windowsSize: WindowWidthSizeClass
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
        contentType,
        navController
    )
}