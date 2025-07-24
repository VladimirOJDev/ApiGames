package com.example.apigames.ui.screens.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.apigames.ui.components.MainTopBar
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.apigames.ui.components.MainImage
import com.example.apigames.ui.components.MetaWebSite
import com.example.apigames.ui.components.ReviewCard
import com.example.apigames.ui.constans.UIConstanst.Companion.CUSTOM_BLACK

@Composable
fun DetailView(
    id: Int?,
    viewModel: HomeViewModel = hiltViewModel(),
    onClickBack:()-> Unit
){

    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {//Se ejeecuta en un hilo secundario cada vez que id cambie su valor o cuando se iniicializa
        if (id != null)
            viewModel.getGameById(id)
    }

    DisposableEffect(Unit){//Se ejecuta cuando sale de compisable
        onDispose {
            viewModel.cleanState()
        }
    }
    Scaffold(
        topBar = {
            MainTopBar(
                title = state.name,
                showBackButton = true,
                onClickBack = onClickBack,
                onClickAction = {}
            )
        }
    ){

        ContentDetailView(state,it)

    }

}

@Composable
fun ContentDetailView(
    state: HomeState,
    padding: PaddingValues
){


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(Color(CUSTOM_BLACK))
    ) {
        MainImage(image = state.backgroundImage)
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 5.dp)
        ){
            MetaWebSite(state.website)
            ReviewCard(metScore = state.metaCritic)
        }
        //Description
        val scroll = rememberScrollState(0)
        Text(
            text = state.descriptionRaw,
            color = Color.White,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(start = 15.dp, end = 15.dp, bottom = 10.dp)
                .verticalScroll(scroll)
        )
    }
}
