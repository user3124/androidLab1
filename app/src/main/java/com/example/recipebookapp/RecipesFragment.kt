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
            1 -> listOf(
                Recipe(1, 1, "Омлет", R.drawable.recipe_omelette,
                    listOf("Яйца", "Молоко", "Соль"),
                    "Взбить яйца с молоком, посолить. Жарить на сковороде 5 минут", "15 мин"),
                Recipe(2, 1, "Каша овсяная", R.drawable.recipe_porridge,
                    listOf("Овсянка", "Молоко", "Сахар"),
                    "Залить овсянку молоком, варить 10 минут, добавить сахар", "20 мин")
            )
            2 -> listOf(
                Recipe(3, 2, "Куриный суп", R.drawable.recipe_chicken_soup,
                    listOf("Курица", "Лук", "Морковь"),
                    "Сварить курицу, добавить овощи, варить 30 минут", "45 мин")
            )
            3 -> listOf(
                Recipe(4, 3, "Блины", R.drawable.recipe_pancakes,
                    listOf("Мука", "Яйца", "Молоко"),
                    "Смешать ингредиенты, жарить на сковороде", "30 мин"),
                Recipe(5, 3, "Шоколадный торт", R.drawable.recipe_chocolate_cake,
                    listOf("Шоколад", "Масло", "Яйца"),
                    "Растопить шоколад, смешать с маслом, выпекать", "60 мин")
            )
            else -> emptyList()
        }
    }
}