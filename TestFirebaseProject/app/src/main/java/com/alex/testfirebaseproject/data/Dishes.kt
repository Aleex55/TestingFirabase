package com.alex.testfirebaseproject.data

data class Dishes (
    val dishUserEmail: String,
    val dishName: String,
    val dishWeight: String,
    val dishRations: String,
    val dishIngredients: List<String>,
    val dishPrice: String,
    val dishDescription: String,
    val dishUserLocation: String
)
