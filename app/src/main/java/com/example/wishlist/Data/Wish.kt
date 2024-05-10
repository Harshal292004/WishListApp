package com.example.wishlist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName="wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id:Long=0L,
    @ColumnInfo(name="wish-title")
    val title:String="",
    @ColumnInfo(name="wish-dsc")
    val description:String=""
)




object DummyWish{
    val wishList=listOf(
        Wish(title ="Google watch 2" ,
            description ="An Android Watch" ),
        Wish(title = "Oculus Quest 2",
            description = "A Vr headset for Android"),
        Wish(title ="A Sci-fi Book" ,
            description ="A Science fiction book" ),
        Wish(title ="Bean Bag",
            description ="tum pade raho ,wo pade rahega" ),
    )
}