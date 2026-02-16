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

        val categories = listOf(
            Category(
                id = 1,
                name = getString(R.string.category_breakfast),
                imageResId = R.drawable.ic_breakfast,
                description = getString(R.string.category_breakfast_desc)
            ),
            Category(
                id = 2,
                name = getString(R.string.category_soup),
                imageResId = R.drawable.ic_soup,
                description = getString(R.string.category_soup_desc)
            ),
            Category(
                id = 3,
                name = getString(R.string.category_dessert),
                imageResId = R.drawable.ic_dessert,
                description = getString(R.string.category_dessert_desc)
            )
        )

        adapter = CategoriesAdapter(categories) { category ->
            val action = CategoriesFragmentDirections.actionCategoriesFragmentToRecipesFragment(category.id)
            findNavController().navigate(action)
        }
        recyclerView.adapter = adapter
    }
}