package com.example.apigames.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.apigames.ui.main.GameContentType
import com.example.apigames.ui.navigation.AppDestinations.DetailView
import com.example.apigames.ui.screens.homeScreen.DetailView
import com.example.apigames.ui.screens.homeScreen.HomeAndDetailView
import com.example.apigames.ui.screens.homeScreen.HomeView
import com.example.apigames.ui.screens.homeScreen.HomeViewModel
import com.example.apigames.ui.screens.homeScreen.SearchGameView

@Composable
fun AppNavGraph(
    viewModel: HomeViewModel,
    navController: NavHostController,
    contentType: GameContentType
){
    NavHost(
        navController =  navController,
        startDestination = AppDestinations.HomeView

    ){
        composable<AppDestinations.HomeView>{
            when(contentType){
                GameContentType.LIST_ONLY ->{
                    HomeView(
                        homeViewModel = viewModel,
                        onClick = {id -> navController.navigate(DetailView(id = id))},
                        onNavigateSearch = {navController.navigate(AppDestinations.SearchGameView)},
                    )

                }
                GameContentType.LIST_AND_DETAIL -> {
                    HomeAndDetailView(
                        viewModel = viewModel,
                        contentType = contentType,
                        onNavigateSearch = {navController.navigate(AppDestinations.SearchGameView)}
                    )
                }
            }

        }


        composable<DetailView>{ backStackEntry->
            val detail: DetailView = backStackEntry.toRoute()
            DetailView(
                id = detail.id,
                viewModel = viewModel
            ) {
                navController.popBackStack()
            }

        }

        composable<AppDestinations.SearchGameView>{
            SearchGameView(
                viewModel = viewModel,
                onBackStack = {navController.popBackStack()},
                onNavigateDetail = {id -> navController.navigate(DetailView(id = id))},
               contentType = contentType
            )
        }
    }
}