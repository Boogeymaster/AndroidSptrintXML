package com.example.androidsptrintxml

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidsptrintxml.databinding.ItemIngredientBinding

class IngredientsAdapter(val ingredientsList: List<Ingredient>) :
    RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemIngredientBinding = ItemIngredientBinding.bind(view)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_ingredient, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        with(viewHolder.binding) {
            tvIngredient.text = ingredientsList[position].description
            val ingredientsCounter =
                "${ingredientsList[position].quantity} ${ingredientsList[position].unitOfMeasure}"
            tvIngredientCounter.text = ingredientsCounter

        }
    }

    override fun getItemCount() = ingredientsList.size
}
