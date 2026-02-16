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

        ingredientsView.text = recipe.ingredients.joinToString("\n☑ ", prefix = "☑ ")

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
                name = "Омлет",
                imageResId = R.drawable.recipe_omelette,
                ingredients = listOf("Яйца - 3 шт", "Молоко - 50 мл", "Соль - по вкусу", "Масло - 20 г"),
                instructions = "Взбейте яйца с молоком и солью\nРазогрейте сковороду с маслом\nВылейте смесь и жарьте 5 минут на среднем огне\nПодавайте горячим",
                cookingTime = "15 мин"
            )
            2 -> Recipe(
                id = 2,
                categoryId = 1,
                name = "Каша овсяная",
                imageResId = R.drawable.recipe_porridge,
                ingredients = listOf("Овсянка - 50 г", "Молоко - 200 мл", "Сахар - 1 ч.л.", "Масло - 10 г"),
                instructions = "Доведите молоко до кипения\nДобавьте овсянку и сахар\nВарите 10 минут, помешивая\nДобавьте масло перед подачей",
                cookingTime = "20 мин"
            )
            3 -> Recipe(
                id = 3,
                categoryId = 2,
                name = "Куриный суп",
                imageResId = R.drawable.recipe_chicken_soup,
                ingredients = listOf("Курица - 300 г", "Картофель - 2 шт", "Морковь - 1 шт", "Лук - 1 шт", "Соль, перец - по вкусу"),
                instructions = "Сварите курицу в 2 л воды (40 мин)\nДобавьте нарезанный картофель\nОбжарьте лук с морковью, добавьте в суп\nВарите еще 15 мин. Посолите, поперчите",
                cookingTime = "45 мин"
            )
            4 -> Recipe(
                id = 4,
                categoryId = 3,
                name = "Блины",
                imageResId = R.drawable.recipe_pancakes,
                ingredients = listOf("Мука - 200 г", "Яйца - 2 шт", "Молоко - 500 мл", "Сахар - 2 ст.л.", "Масло - 2 ст.л.", "Соль - щепотка"),
                instructions = "Смешайте яйца, сахар и соль\nДобавьте молоко, перемешайте\nПостепенно добавьте муку, взбивая венчиком\nВлейте масло, дайте постоять 15 мин\nЖарьте на сковороде до золотистого цвета",
                cookingTime = "30 мин"
            )
            5 -> Recipe(
                id = 5,
                categoryId = 3,
                name = "Шоколадный торт",
                imageResId = R.drawable.recipe_chocolate_cake,
                ingredients = listOf("Шоколад - 200 г", "Масло - 200 г", "Яйца - 4 шт", "Сахар - 150 г", "Мука - 100 г"),
                instructions = "Растопите шоколад с маслом\nВзбейте яйца с сахаром\nСмешайте все, добавьте муку\nВыпекайте при 180°C 30-35 мин\nДайте остыть перед подачей",
                cookingTime = "60 мин"
            )
            else -> Recipe(
                id = 0,
                categoryId = 0,
                name = "Рецепт не найден",
                imageResId = R.drawable.ic_launcher_foreground,
                ingredients = emptyList(),
                instructions = "Извините, рецепт не найден",
                cookingTime = "0 мин"
            )
        }
    }
}