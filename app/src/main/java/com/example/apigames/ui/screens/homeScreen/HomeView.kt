package com.example.apigames.ui.screens.homeScreen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.apigames.ui.components.CardGame
import com.example.apigames.ui.components.MainTopBar
import com.example.apigames.ui.constans.UIConstanst.Companion.CUSTOM_BLACK


@Composable
fun HomeView(
    homeViewModel: HomeViewModel = hiltViewModel(),
    onClick: (Int)-> Unit,
    onNavigateSearch:()-> Unit
    ){
    Scaffold(
        topBar =  {
            MainTopBar(
                title = "ApiGames",
                onClickBack = {},
                onClickAction = onNavigateSearch
            )
        }
    ) { padding->
        ContentHomeView(homeViewModel,padding, onClick)

    }

}

@Composable
fun ContentHomeView(homeViewModel: HomeViewModel, padding: PaddingValues, onClick:(Int)-> Unit){
    val uiState by homeViewModel.uiState.collectAsState()
    val listGames = uiState.listGames

    LazyColumn(modifier = Modifier.padding(padding).background(Color(CUSTOM_BLACK))){
        items(listGames){item->
            CardGame(
              game = item, onclick = {onClick(item.id)}
            )
            Text(
                text = item.name,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}
