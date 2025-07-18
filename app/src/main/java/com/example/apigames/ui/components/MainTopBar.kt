package com.example.apigames.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.apigames.ui.constans.UIConstanst.Companion.CUSTOM_BLACK

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    title:String,
    showBackButton: Boolean= false,
    onClickBack: ()-> Unit,
    onClickAction: ()-> Unit
){
    TopAppBar(
        title = {
            Text(
            text = title,
            color = Color.White,
            fontWeight = FontWeight.ExtraBold
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color(CUSTOM_BLACK)
        ),
        navigationIcon = {
            if(showBackButton){
                IconButton(onClick = onClickBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        tint = Color.White,
                        contentDescription = null
                    )
                }
            }

        },
        actions = {
            if(!showBackButton){
                IconButton(onClick = onClickAction) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        tint = Color.White,
                        contentDescription = null
                    )
                }
            }
        }//Iconos de la derecha del appbar
    )
}


//@Preview(showSystemUi = true)
//@Composable
//fun PreviewHomeView(){
//    MainTopBar("Titulo",true) { }
//}