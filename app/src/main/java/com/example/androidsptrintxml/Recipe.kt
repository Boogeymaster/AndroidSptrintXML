package com.example.androidsptrintxml

data class Recipe(
    val id: Int,
    val title: String,
    val ingredients: Ingredient,
    val method: String,
    val imageUrl: String,
)
