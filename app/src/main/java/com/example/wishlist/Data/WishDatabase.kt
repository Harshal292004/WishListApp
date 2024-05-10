package com.example.wishlist.Data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.wishlist.Wish


@Database(
    entities = [Wish::class],
    version = 1,
    exportSchema = false
)
abstract class WishDatabase :RoomDatabase(){
    abstract  fun wishDao():WishDao
}

