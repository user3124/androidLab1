package com.example.recipebookapp.models

data class Recipe(
    val id: Int,
    val categoryId: Int, // чтобы знать, к какой категории относится рецепт
    val name: String,
    val imageResId: Int,
    val ingredients: List<String>,
    val instructions: String,
    val cookingTime: String // например, "30 мин"
)