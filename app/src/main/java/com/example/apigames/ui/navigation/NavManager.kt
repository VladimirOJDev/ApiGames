package com.example.apigames.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.apigames.ui.navigation.AppDestinations.DetailView
import com.example.apigames.ui.screens.homeScreen.HomeView
import com.example.apigames.ui.screens.detailScreen.DetailView
import com.example.apigames.ui.screens.searchgameview.SearchGameView

@Composable
fun NavManager(
    navController: NavHostController = rememberNavController()
){

    NavHost(navController =  navController, startDestination = AppDestinations.HomeView){
        composable<AppDestinations.HomeView>{
            HomeView(
                onClick = {id -> navController.navigate(DetailView(id = id))},
                onNavigateSearch = {navController.navigate(AppDestinations.SearchGameView)},
            )
        }

        composable<DetailView>{ backStackEntry->
            val detail: DetailView = backStackEntry.toRoute()
            DetailView(detail.id) {
                navController.popBackStack()
            }
        }

        composable<AppDestinations.SearchGameView>{
            SearchGameView(
                onBackStack = {navController.popBackStack()},
                onNavigateDetail = {id -> navController.navigate(DetailView(id = id))}
            )
        }
    }

}