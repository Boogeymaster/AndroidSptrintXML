package com.example.androidsptrintxml

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidsptrintxml.databinding.ItemIngredientBinding
import java.math.BigDecimal

class IngredientsAdapter(val ingredientsList: List<Ingredient>) :
    RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {
    var quantity: Int = 1

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
            val ingredientsQuantity = BigDecimal(ingredientsList[position].quantity).multiply(
                BigDecimal(quantity))
            val ingredientsCounter = if (ingredientsQuantity % BigDecimal(1) > BigDecimal(0)) {
                "${ingredientsQuantity.setScale(1)}  ${ingredientsList[position].unitOfMeasure}"
            } else {
                "${ingredientsQuantity.toInt()}  ${ingredientsList[position].unitOfMeasure}"
            }
            tvIngredientCounter.text = ingredientsCounter
        }
    }

    override fun getItemCount() = ingredientsList.size

    fun updateIngredients(progress: Int) {
        quantity = progress
    }
}
