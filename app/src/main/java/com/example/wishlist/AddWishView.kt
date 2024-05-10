package com.example.wishlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun AddWishView(
    id:Long,
    viewModel: WishViewModel,
    navController: NavController
){

    val snackMessage=remember{
        mutableStateOf("")
    }

    val scope=rememberCoroutineScope()

    val scaffoldState= rememberScaffoldState()

    if(id !=0L) {
        val wish = viewModel.getAWishById(id).collectAsState(initial = Wish(0L,"",""))
        viewModel.wishTitleState=wish.value.title
        viewModel.wishDescriptionState=wish.value.description
    }
    else{
        viewModel.wishTitleState=""
        viewModel.wishDescriptionState=""
    }


    Scaffold(
        scaffoldState=scaffoldState,
            topBar = { AppBarView(title =
                if(id!=0L) stringResource(id = R.string.update_wish)
                else stringResource(id = R.string.add_wish), onBackNavClicked ={navController.navigateUp()}
                )
            }){
        Column(modifier= Modifier
            .padding(it)
            .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
            ){
            Spacer(modifier = Modifier.height(10.dp))

            WishTextField(label = "Title",
                value = viewModel.wishTitleState,
                onValueChanged ={
                    viewModel.onWishTitleChanged(it)
                } )

            Spacer(modifier = Modifier.height(10.dp))

            WishTextField(label = "Description",
                value = viewModel.wishDescriptionState,
                onValueChanged ={
                    viewModel.onWishDescriptionStateChanged(it)
                } )

            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                if(viewModel.wishTitleState.isNotEmpty()&&viewModel.wishDescriptionState.isNotEmpty()){
                    if(id!=0L){
                        //TODO Update Wish
                        viewModel.updateWish(
                            Wish(
                                id=id,
                                title = viewModel.wishTitleState.trim(),
                                description = viewModel.wishDescriptionState.trim()
                            )
                        )
                        snackMessage.value = "Wish has been Updated"
                    }
                    else{
                        //TODO Add Wish
                        viewModel.addWish(
                            Wish(
                                title = viewModel.wishTitleState.trim(),
                                description =viewModel.wishDescriptionState.trim()
                            )
                        )
                        snackMessage.value="Wish has been created"
                    }
                    navController.navigateUp()
                } else{
                    //Enter fields for wish to bee created
                    snackMessage.value="Enter fields for wish to bee created"
                }
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                }
            }){
                Text(
                    text= if(id!=0L) stringResource(id = R.string.update_wish)
                    else stringResource(id =R.string.add_wish),
                    style = TextStyle(
                        fontSize = 18.sp
                    )

                )
            }
        }
    }
}

@Composable
fun WishTextField(
    label:String ,
    value:String ,
    onValueChanged:(String) ->Unit
){
    OutlinedTextField(
        value = value,
        onValueChange =onValueChanged,
        label={Text(text=label, color = Color.Black)},
        modifier=Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors=TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color(0xFFC2185B), // Deep pink for text
            focusedBorderColor = Color(0xFF880E4F), // Darker shade of pink for focus
            unfocusedBorderColor = Color(0xFFC2185B), // Deep pink for unfocused border
            cursorColor = Color(0xFF880E4F), // Darker shade of pink for cursor
            focusedLabelColor = Color(0xFFE91E63), // Brighter shade of pink for label on focus
            unfocusedLabelColor = Color(0xFFC2185B) // Deep pink for label when not focused
        )
    )
}