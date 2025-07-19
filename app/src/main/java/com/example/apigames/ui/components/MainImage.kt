package com.example.apigames.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun MainImage(image: String){
    val image = rememberImagePainter(data = image )

    Image(
        painter = image,
        contentDescription = null,
        contentScale = ContentScale.Crop, // Hace que la imagen se expanda deacuerdo con el contenedor
        modifier = Modifier.fillMaxWidth().height(250.dp)
    )
}