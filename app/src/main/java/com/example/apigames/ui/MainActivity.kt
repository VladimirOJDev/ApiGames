package com.example.apigames.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.apigames.ui.navigation.AppNavGraph
import com.example.apigames.ui.theme.ApiGamesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // Macro para la inyeccon de dependedncias
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApiGamesTheme {
                AppNavGraph()
            }
        }
    }
}
