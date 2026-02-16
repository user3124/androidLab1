package com.example.recipebookapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.recipebookapp.models.Category
import com.example.recipebookapp.theme.RecipeBookTheme

class ComposeCategoriesFragment : Fragment() {

    // Состояние темы
    private var isDarkTheme by mutableStateOf(false)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                RecipeBookTheme(darkTheme = isDarkTheme) {
                    Surface(
                        color = MaterialTheme.colors.background,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CategoriesScreen(
                            onCategoryClick = { categoryId ->
                                val action = ComposeCategoriesFragmentDirections
                                    .actionCategoriesFragmentToRecipesFragment(categoryId)
                                findNavController().navigate(action)
                            },
                            onThemeChanged = { dark ->
                                isDarkTheme = dark
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CategoriesScreen(
    onCategoryClick: (Int) -> Unit,
    onThemeChanged: (Boolean) -> Unit  // Новая функция для смены темы
) {
    val categories = listOf(
        Category(
            id = 1,
            name = stringResource(R.string.category_breakfast),
            imageResId = R.drawable.ic_breakfast,
            description = stringResource(R.string.category_breakfast_desc)
        ),
        Category(
            id = 2,
            name = stringResource(R.string.category_soup),
            imageResId = R.drawable.ic_soup,
            description = stringResource(R.string.category_soup_desc)
        ),
        Category(
            id = 3,
            name = stringResource(R.string.category_dessert),
            imageResId = R.drawable.ic_dessert,
            description = stringResource(R.string.category_dessert_desc)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.title_categories),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(vertical = 24.dp)
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(categories) { category ->
                CategoryCard(
                    category = category,
                    onClick = { onCategoryClick(category.id) }
                )
            }
        }
        AndroidView(
            factory = { context ->
                LayoutInflater.from(context)
                    .inflate(R.layout.theme_buttons, null)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            update = { view ->
                view.findViewById<Button>(R.id.lightThemeButton)?.setOnClickListener {
                    onThemeChanged(false)  // светлая
                    Toast.makeText(view.context, "Светлая тема", Toast.LENGTH_SHORT).show()
                }
                view.findViewById<Button>(R.id.darkThemeButton)?.setOnClickListener {
                    onThemeChanged(true)   // темная
                    Toast.makeText(view.context, "Темная тема", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}

@Composable
fun CategoryCard(
    category: Category,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = category.imageResId),
                contentDescription = category.name,
                modifier = Modifier
                    .size(70.dp)
                    .padding(end = 16.dp)
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = category.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onSurface
                )
                Text(
                    text = category.description,
                    fontSize = 15.sp,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            Text(
                text = "→",
                fontSize = 24.sp,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}