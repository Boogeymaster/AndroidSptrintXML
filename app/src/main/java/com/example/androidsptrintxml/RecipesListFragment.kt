package com.example.androidsptrintxml

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidsptrintxml.databinding.FragmentListRecipeBinding
import java.io.InputStream

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
        with(binding){
            try {
                val inputStream: InputStream? =
                    categoryUrl?.let { view.context?.assets?.open(it) }
                val drawable = Drawable.createFromStream(inputStream, null)
                ivRecipesHeader.setImageDrawable(drawable)
            } catch (e: Exception) {
                Log.e("error", "Error drawable in onBindViewHolder", e)
            }
            tvRecipesHeader.text = categoryTitle
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}