package com.example.wishlist.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.example.wishlist.Wish
import  androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
abstract  class WishDao {

    //the on conflict determines the way conflicts are resolved
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addWish(wishEntity: Wish)

    //Loads all wishes from the wish table
    @Query("Select * from `wish-table`")
    abstract  fun getAllWishes(): Flow<List<Wish>>

    @Update
    abstract  suspend  fun updateWish(wishEntity: Wish)

    @Delete
    abstract fun deleteWish(wishEntity: Wish)

    @Query("Select * from `wish-table` where id=:id")
    abstract  fun getAWishById(id:Long):Flow<Wish>
}