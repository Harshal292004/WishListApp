package com.example.wishlist

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp


//for the conversion of image to image vector :
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController


@Composable
fun floatingActionButton(navController: NavController,viewModel: WishViewModel){
    val image: Painter = painterResource(id = R.drawable.quil)
    FloatingActionButton(
        modifier = Modifier.padding(all=20.dp),
        contentColor = Color.White,
        backgroundColor= colorResource(id = R.color.app_bar_color),
        onClick = {
            navController.navigate(Screen.AddScreen.route+"/0L")
        }) {
       Icon(image, contentDescription = "Quil",modifier=Modifier.size(40.dp))
    }


}


