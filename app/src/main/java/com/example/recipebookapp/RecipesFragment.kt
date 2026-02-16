package com.example.recipebookapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipebookapp.adapters.RecipesAdapter
import com.example.recipebookapp.models.Recipe

class RecipesFragment : Fragment() {

    private val args: RecipesFragmentArgs by navArgs()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecipesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerViewRecipes)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val recipes = getRecipesForCategory(args.categoryId)

        adapter = RecipesAdapter(recipes) { recipe ->
            val action = RecipesFragmentDirections.actionRecipesFragmentToRecipeDetailFragment(recipe.id)
            findNavController().navigate(action)
        }
        recyclerView.adapter = adapter
    }

    private fun getRecipesForCategory(categoryId: Int): List<Recipe> {
        return when (categoryId) {
            1 -> listOf( // Завтраки
                Recipe(
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
                ),
                Recipe(
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
            )
            2 -> listOf( // Супы
                Recipe(
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
            )
            3 -> listOf( // Десерты
                Recipe(
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
                ),
                Recipe(
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
            )
            else -> emptyList()
        }
    }
}