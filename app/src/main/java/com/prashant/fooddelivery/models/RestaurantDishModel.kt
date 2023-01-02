package com.prashant.fooddelivery.models

data class RestaurantDishModel(
    val restaurantName: String,
    val restaurantOffers: String,
    val stars: Float,
    val comments: Long=0,
    val sales: Long=0,
    val icon: Int,
    val isRestaurant:Boolean
)
