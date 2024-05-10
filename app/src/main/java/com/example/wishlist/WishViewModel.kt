package com.example.wishlist

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewModelScope
import com.example.wishlist.Data.WishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel(
    private  val wishRepository: WishRepository=Graph.wishRepository
) : ViewModel(){

    var wishTitleState by mutableStateOf("")//For the title of the wish
    var wishDescriptionState by mutableStateOf("")//for the description of the title

    fun onWishTitleChanged(newString:String){
        wishTitleState=newString
    }

    fun onWishDescriptionStateChanged(newString:String){
        wishDescriptionState=newString
    }


    lateinit var getAllWishes: Flow<List<Wish>>

    init{
        //launching different coroutines
        viewModelScope.launch {
            getAllWishes=wishRepository.getWishes()
        }
    }


    fun addWish(wish:Wish){
        //launching different coroutines using the viewModel scope ,
        viewModelScope.launch(Dispatchers.IO){
            wishRepository.addAWish(wish=wish)
        }
    }


    fun getAWishById(id:Long):Flow<Wish>{
        return wishRepository.getAWishById(id)
    }

    fun updateWish(wish:Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.updateAWish(wish=wish)
        }
    }

    fun deleteWish(wish:Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.deleteAWish(wish=wish)
        }
    }
}