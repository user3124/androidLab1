package com.example.recipebookapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipebookapp.adapters.CategoriesAdapter
import com.example.recipebookapp.models.Category

class CategoriesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerViewCategories)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Создаем тестовые данные
        val categories = listOf(
            Category(1, "Завтраки", R.drawable.ic_breakfast, "Вкусные и полезные завтраки"),
            Category(2, "Супы", R.drawable.ic_soup, "Горячие и холодные супы"),
            Category(3, "Десерты", R.drawable.ic_dessert, "Сладкие угощения")
        )

        adapter = CategoriesAdapter(categories) { category ->
            val action = CategoriesFragmentDirections.actionCategoriesFragmentToRecipesFragment(category.id)
            findNavController().navigate(action)
        }
        recyclerView.adapter = adapter
    }
}