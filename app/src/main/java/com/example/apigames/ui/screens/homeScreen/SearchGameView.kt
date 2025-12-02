package com.example.apigames.ui.screens.homeScreen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apigames.ui.main.GameContentType


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchGameView(
    viewModel: HomeViewModel,
    onBackStack:()->Unit,
    onNavigateDetail: (Int)-> Unit,
    contentType: GameContentType
){

    val state by viewModel.uiState.collectAsState()
    val games = state.listGames

    var query by remember { mutableStateOf("") }
    // Estado para controlar si la barra de búsqueda está expandida (activa)
    var expanded by remember{ mutableStateOf(false) }

    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        inputField = {
            // Campo de entrada del SearchBar
            SearchBarDefaults.InputField(
                query = query,
                onQueryChange = { query = it },
                onSearch = { expanded = false }, // Cierra el SearchBar al buscar
                expanded = expanded, // Controla la expansión del InputField
                onExpandedChange = { expanded = it },
                placeholder = { Text("Buscar") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = null,
                        modifier = Modifier.clickable { onBackStack() }
                    )
                },
            )
        },
        expanded = expanded, // Controla la expansión del SearchBar principal
        onExpandedChange = { expanded = it },
        // Este es el bloque de contenido que aparece cuando el SearchBar está expandido.
        // En tu ejemplo lo dejaste vacío, así que aquí también está vacío.
    ) {
        if (query.isNotEmpty()){
            val filterGames = games.filter { it.name.contains(query, ignoreCase = true) }
            filterGames.forEach {
                Text(text = it.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 10.dp, start = 10.dp)
                        .clickable{
                            when(contentType){
                                GameContentType.LIST_ONLY ->{onNavigateDetail(it.id)}
                                GameContentType.LIST_AND_DETAIL -> {

                                    viewModel.getGameById(it.id)
                                    onBackStack()
                                }
                            }
                        }
                )
            }
        }
    }
}

