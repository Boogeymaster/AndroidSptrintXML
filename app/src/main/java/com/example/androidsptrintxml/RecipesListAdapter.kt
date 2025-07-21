package com.example.androidsptrintxml

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidsptrintxml.databinding.ItemRecipeBinding
import java.io.InputStream

class RecipesListAdapter(private val recipesList: List<Recipe>?) :
    RecyclerView.Adapter<RecipesListAdapter.ViewHolder>() {
    var itemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(recipeId: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemRecipeBinding = ItemRecipeBinding.bind(view)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_recipe, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        with(viewHolder.binding) {
            try {
                val inputStream: InputStream? =
                    viewHolder.itemView.context?.assets?.open(recipesList?.get(position)?.imageUrl
                        ?: return)
                val drawable = Drawable.createFromStream(inputStream, null)
                ivRecipe.setImageDrawable(drawable)
            } catch (e: Exception) {
                Log.e("error", "Error drawable in onBindViewHolder", e)
            }
            tvRecipeTitle.text = recipesList?.get(position)?.title
            root.setOnClickListener {
                itemClickListener?.onItemClick(recipesList?.get(position)?.id ?: return@setOnClickListener)
            }
        }
    }

    override fun getItemCount() = recipesList?.size ?: 0
}