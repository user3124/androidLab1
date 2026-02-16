package com.example.recipebookapp.models

data class Category(
    val id: Int,
    val name: String,
    val imageResId: Int, // ID картинки из ресурсов (drawable)
    val description: String
)