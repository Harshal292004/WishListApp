package com.example.wishlist.Data

import com.example.wishlist.Wish
import kotlinx.coroutines.flow.Flow

class WishRepository(private  val wishDao: WishDao){

    suspend  fun addAWish(wish: Wish){
        wishDao.addWish(wish)
    }

    fun getWishes(): Flow<List<Wish>> = wishDao.getAllWishes()

    fun getAWishById(id:Long):Flow<Wish>{
        return  wishDao.getAWishById(id)
    }

    suspend fun updateAWish(wish:Wish) {
        wishDao.updateWish(wish)
    }

    suspend fun deleteAWish(wish:Wish){
        wishDao.deleteWish(wish)
    }
}