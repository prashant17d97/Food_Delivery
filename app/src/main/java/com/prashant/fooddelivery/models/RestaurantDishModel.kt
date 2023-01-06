package com.prashant.fooddelivery.models

import com.prashant.fooddelivery.R

data class RestaurantDishModel(
    val restaurantName: String="",
    val restaurantOffers: String="",
    val stars: Float=0.0f,
    val comments: Long=0,
    val sales: Long=0,
    val icon: Int= 0,
    val isRestaurant:Boolean=false
)
