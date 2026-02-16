package com.example.recipebookapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.recipebookapp.models.Recipe

class RecipeDetailFragment : Fragment() {

    private val args: RecipeDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipe_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView = view.findViewById<ImageView>(R.id.detailImage)
        val nameView = view.findViewById<TextView>(R.id.detailName)
        val timeView = view.findViewById<TextView>(R.id.detailTime)
        val ingredientsView = view.findViewById<TextView>(R.id.detailIngredients)
        val instructionsView = view.findViewById<TextView>(R.id.detailInstructions)

        val recipe = getRecipeById(args.recipeId)

        imageView.setImageResource(recipe.imageResId)
        nameView.text = recipe.name
        timeView.text = recipe.cookingTime
        ingredientsView.text = recipe.ingredients.joinToString("\n")

        val numberedInstructions = recipe.instructions
            .split("\n")
            .mapIndexed { index, line -> "${index + 1}) ${line.trim()}" }
            .joinToString("\n")
        instructionsView.text = numberedInstructions
    }

    private fun getRecipeById(recipeId: Int): Recipe {
        return when (recipeId) {
            1 -> Recipe(
                id = 1,
                categoryId = 1,
                name = getString(R.string.recipe_omelette),
                imageResId = R.drawable.recipe_omelette,
                ingredients = listOf(
                    getString(R.string.ingredient_eggs),
                    getString(R.string.ingredient_milk),
                    getString(R.string.ingredient_salt),
                    getString(R.string.ingredient_oil)
                ),
                instructions = getString(R.string.instructions_omelette),
                cookingTime = getString(R.string.time_15min)
            )
            2 -> Recipe(
                id = 2,
                categoryId = 1,
                name = getString(R.string.recipe_porridge),
                imageResId = R.drawable.recipe_porridge,
                ingredients = listOf(
                    getString(R.string.ingredient_oatmeal),
                    getString(R.string.ingredient_milk),
                    getString(R.string.ingredient_sugar),
                    getString(R.string.ingredient_oil)
                ),
                instructions = getString(R.string.instructions_porridge),
                cookingTime = getString(R.string.time_20min)
            )
            3 -> Recipe(
                id = 3,
                categoryId = 2,
                name = getString(R.string.recipe_chicken_soup),
                imageResId = R.drawable.recipe_chicken_soup,
                ingredients = listOf(
                    getString(R.string.ingredient_chicken),
                    getString(R.string.ingredient_potato),
                    getString(R.string.ingredient_carrot),
                    getString(R.string.ingredient_onion),
                    getString(R.string.ingredient_salt_pepper)
                ),
                instructions = getString(R.string.instructions_chicken_soup),
                cookingTime = getString(R.string.time_45min)
            )
            4 -> Recipe(
                id = 4,
                categoryId = 3,
                name = getString(R.string.recipe_pancakes),
                imageResId = R.drawable.recipe_pancakes,
                ingredients = listOf(
                    getString(R.string.ingredient_flour),
                    getString(R.string.ingredient_eggs),
                    getString(R.string.ingredient_milk),
                    getString(R.string.ingredient_sugar),
                    getString(R.string.ingredient_oil),
                    getString(R.string.ingredient_salt_pinch)
                ),
                instructions = getString(R.string.instructions_pancakes),
                cookingTime = getString(R.string.time_30min)
            )
            5 -> Recipe(
                id = 5,
                categoryId = 3,
                name = getString(R.string.recipe_chocolate_cake),
                imageResId = R.drawable.recipe_chocolate_cake,
                ingredients = listOf(
                    getString(R.string.ingredient_chocolate),
                    getString(R.string.ingredient_butter),
                    getString(R.string.ingredient_eggs),
                    getString(R.string.ingredient_sugar),
                    getString(R.string.ingredient_flour)
                ),
                instructions = getString(R.string.instructions_chocolate_cake),
                cookingTime = getString(R.string.time_60min)
            )
            else -> Recipe(
                id = 0,
                categoryId = 0,
                name = getString(R.string.recipe_not_found),
                imageResId = R.drawable.ic_launcher_foreground,
                ingredients = emptyList(),
                instructions = getString(R.string.instructions_not_found),
                cookingTime = getString(R.string.time_0min)
            )
        }
    }
}