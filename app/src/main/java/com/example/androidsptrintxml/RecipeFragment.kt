package com.example.androidsptrintxml

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.androidsptrintxml.databinding.FragmentRecipeBinding
import com.google.android.material.divider.MaterialDividerItemDecoration
import java.io.InputStream

class RecipeFragment : Fragment() {
    private var _binding: FragmentRecipeBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("binding for RecipesFragment is null")
    private var recipe: Recipe? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        recipe = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getParcelable(ARG_RECIPE, Recipe::class.java)
        } else {
            requireArguments().getParcelable(ARG_RECIPE)
        }
        _binding = FragmentRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initRecyclers()
    }

    fun initUI() {
        try {
            val inputStream: InputStream? =
                view?.rootView?.context?.assets?.open(recipe?.imageUrl ?: "null")
            val drawable = Drawable.createFromStream(inputStream, null)
            binding.ivRecipeHeader.setImageDrawable(drawable)
        } catch (e: Exception) {
            Log.e("error", "Error drawable in RecipeFragment", e)
        }
        setFavoriteDrawable(recipe?.isFavorite)
        with(binding) {
            tvRecipeHeader.text = recipe?.title
            ibIsFavorites.setOnClickListener {
                recipe?.isFavorite?.let { it1 -> recipe?.isFavorite = !it1 }
                setFavoriteDrawable(recipe?.isFavorite)
            }
        }
    }

    fun setFavoriteDrawable(isFavorite: Boolean?) {
        if (isFavorite ?: false) {
            binding.ibIsFavorites.setImageDrawable(
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.ic_heart_40,
                    null
                )
            )
        } else {
            binding.ibIsFavorites.setImageDrawable(
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.ic_heart_empty_40,
                    null
                )
            )
        }
    }

    fun initRecyclers() {
        val ingredientsAdapter = IngredientsAdapter(recipe?.ingredients ?: listOf())
        val ingredientsRecycler: RecyclerView = binding.rvIngredients
        val dividerItemDecoration = MaterialDividerItemDecoration(
            ingredientsRecycler.context,
            RecyclerView.VERTICAL
        )
        dividerItemDecoration.apply {
            this.dividerColor =
                ContextCompat.getColor(ingredientsRecycler.context, R.color.divider_color)
            this.isLastItemDecorated = false
            this.dividerInsetStart = resources.getDimensionPixelSize(R.dimen.margin_divider)
            this.dividerInsetEnd = resources.getDimensionPixelSize(R.dimen.margin_divider)
        }
        ingredientsRecycler.addItemDecoration(dividerItemDecoration)
        ingredientsRecycler.adapter = ingredientsAdapter

        val methodAdapter = MethodsAdapter(recipe?.method ?: listOf())
        val methodsRecycler: RecyclerView = binding.rvMethods
        methodsRecycler.addItemDecoration(dividerItemDecoration)
        methodsRecycler.adapter = methodAdapter
        binding.sbPortionCount.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                binding.tvPortionCount.text = "$progress"
                ingredientsAdapter.updateIngredients(progress)
                ingredientsAdapter.notifyDataSetChanged()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}