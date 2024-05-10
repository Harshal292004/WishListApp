package com.example.wishlist



//this classes contains multiple routes for navigation between screens
//and sealed to avoid inheritance
sealed class Screen(val route:String) {
    object HomeScreen:Screen("home_screen")
    object AddScreen:Screen("add_screen")

}