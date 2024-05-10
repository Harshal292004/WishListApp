package com.example.wishlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Scaffold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


//the main view where all the wishes are displayed
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeView(navController: NavController,viewModel: WishViewModel){
    Scaffold(
        topBar = {
            AppBarView(title = "Wish List")
        },
        floatingActionButton = {
            floatingActionButton(navController,viewModel)
        }
    ){
        //loading all the data from the room
        val wishList=viewModel.getAllWishes.collectAsState(initial = listOf())


        LazyColumn(modifier= Modifier
            .fillMaxSize()
            .padding(it)){
            items(wishList.value){
                wish->

                /*val dismissState= rememberDismissState(
                    confirmStateChange = {
                        if(it== DismissValue.DismissedToEnd|| it==DismissValue.DismissedToStart){
                            viewModel.deleteWish(wish)
                        }
                        true
                    }
                )
                SwipeToDismiss(
                    state = dismissState,
                    background = {},
                    directions = setOf(DismissDirection.StartToEnd,DismissDirection.EndToStart),
                    dismissThresholds = {FractionalThreshold(0.25f)},
                    dismissContent = {
                        WishItem(wish=wish){
                            val id=wish.id
                            navController.navigate(Screen.AddScreen.route+"/$id")
                        }
                    }
                )*/
                WishItem(wish = wish) {
                    val id=wish.id
                    navController.navigate(Screen.AddScreen.route+"/$id")
                }
            }

        }
    }
}




//the wish list item card
@Composable
fun WishItem(wish:Wish,onClick:()->Unit){
    Card(modifier= Modifier
        .fillMaxWidth()
        .padding(top = 8.dp, start = 8.dp, end = 8.dp)
        .clickable {
            onClick()
        },
        elevation = 10.dp,
        backgroundColor = Color.White
        ) {

        Column(modifier=Modifier.padding(16.dp)) {
            Text(
                text=wish.title,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = wish.description
             )
        }
        
    }
}