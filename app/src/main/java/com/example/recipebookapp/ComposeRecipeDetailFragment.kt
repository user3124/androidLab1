package com.example.recipebookapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.recipebookapp.models.Recipe
import com.example.recipebookapp.theme.RecipeBookTheme

class ComposeRecipeDetailFragment : Fragment() {

    private val args: ComposeRecipeDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                RecipeBookTheme {
                    Surface(
                        color = MaterialTheme.colors.background
                    ) {
                        RecipeDetailScreen(recipeId = args.recipeId)
                    }
                }
            }
        }
    }

    @Composable
    fun RecipeDetailScreen(recipeId: Int) {
        val recipe = getRecipeById(recipeId)
        val checkStates = remember {
            mutableStateListOf<Boolean>().apply {
                repeat(recipe.ingredients.size) { add(false) }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(id = recipe.imageResId),
                contentDescription = recipe.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp),
                contentScale = ContentScale.Crop
            )

            // Контент
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp)
            ) {
                Text(
                    text = recipe.name,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = recipe.cookingTime,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary,  // accent_green = primary
                    modifier = Modifier.padding(bottom = 24.dp)
                )
                Text(
                    text = stringResource(R.string.title_ingredients),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                recipe.ingredients.forEachIndexed { index, ingredient ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = checkStates[index],
                            onCheckedChange = { checkStates[index] = it },
                            modifier = Modifier
                                .size(24.dp)  // размер по умолчанию
                                .padding(end = 8.dp)
                        )
                        Text(
                            text = ingredient,
                            fontSize = 15.sp,
                            color = MaterialTheme.colors.onBackground.copy(alpha = 0.8f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Заголовок "Приготовление"
                Text(
                    text = stringResource(R.string.title_instructions),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                // Инструкции
                val instructions = recipe.instructions.split("\n")
                instructions.forEachIndexed { index, instruction ->
                    Text(
                        text = "${index + 1}) $instruction",
                        fontSize = 15.sp,
                        color = MaterialTheme.colors.onBackground.copy(alpha = 0.8f),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }
        }
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