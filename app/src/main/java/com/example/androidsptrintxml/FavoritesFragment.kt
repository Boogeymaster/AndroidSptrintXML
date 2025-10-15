package com.example.androidsptrintxml

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.recyclerview.widget.RecyclerView
import com.example.androidsptrintxml.databinding.FragmentFavoritesBinding

class FavoritesFragment : Fragment() {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("binding for FavoritesFragment is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun initRecycler() {
        val recipes = RecipeFragment().getFavorites(requireContext())
        val stub = STUB
        val recycler: RecyclerView = binding.rvFavoriteRecipes
        val recyclerAdapter = RecipesListAdapter(stub.getRecipesByIds(recipes))
        recycler.adapter = recyclerAdapter
        recyclerAdapter.setOnItemClickListener(object :
            RecipesListAdapter.OnItemClickListener {
            override fun onItemClick(recipeId: Int) {
                openRecipeByRecipeId(recipeId)
            }
        })
    }

    fun openRecipeByRecipeId(recipeId: Int) {
        val stub = STUB
        val recipe = stub.getRecipeById(recipeId)
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            val bundle = bundleOf(ARG_RECIPE to recipe)
            replace<RecipeFragment>(R.id.mainContainer, args = bundle)
        }
    }
}