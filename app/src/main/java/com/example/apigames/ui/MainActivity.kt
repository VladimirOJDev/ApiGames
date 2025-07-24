package com.example.apigames.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.example.apigames.ui.main.GameApp
import com.example.apigames.ui.theme.ApiGamesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // Macro para la inyeccon de dependedncias
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApiGamesTheme {
                val windowsSize = calculateWindowSizeClass(this)
                GameApp(windowsSize.widthSizeClass)
            }
        }
    }
}
