package com.example.androidsptrintxml

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidsptrintxml.databinding.FragmentListRecipeBinding

class RecipesListFragment : Fragment() {

    private var _binding: FragmentListRecipeBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("binding for RecipesListFragment is null")
    private var categoryId: Int? = null
    private var categoryTitle: String? = null
    private var categoryUrl: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListRecipeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        categoryId = requireArguments().getInt(ARG_CATEGORY_ID)
        categoryTitle = requireArguments().getString(ARG_CATEGORY_NAME)
        categoryUrl = requireArguments().getString(ARG_CATEGORY_IMAGE_URL)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}