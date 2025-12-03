package com.example.apigames.ui.screens.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apigames.ui.main.GameContentType
import androidx.compose.runtime.getValue
import com.example.apigames.ui.components.MainTopBar


@Composable
fun HomeAndDetailView(
    viewModel: HomeViewModel,
    contentType: GameContentType,
    onNavigateSearch: ()-> Unit
){
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            MainTopBar(
                title = uiState.name,
                showBackButton = false,
                onClickAction = onNavigateSearch,
                onClickBack = {}
            )
        }
    ){
        Row(
            modifier = Modifier.fillMaxSize().background(color = Color.Black),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .background(Color.Black)
            ){
                ContentHomeView(
                    homeViewModel = viewModel,
                    contentType = contentType,
                    padding = it,
                    ) {}
            }
            Spacer(modifier = Modifier.width(8.dp).background(Color.Black)) //spacer vertical
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .background(Color.Black),
                contentAlignment = Alignment.Center

            ){
                ContentDetailView(
                    viewModel= viewModel,
                    padding = it
                )
            }
        }
    }
}

@Preview(device = "spec:width=1280dp,height=800dp,dpi=240")
@Composable
fun PreviewHomeAndDetail(){
//    Scaffold {
//        HomeAndDetailView(it)
//    }
}