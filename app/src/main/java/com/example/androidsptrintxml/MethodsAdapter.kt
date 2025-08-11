package com.example.androidsptrintxml

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidsptrintxml.databinding.ItemMethodBinding

class MethodsAdapter(val methodsList: List<String>) :
    RecyclerView.Adapter<MethodsAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemMethodBinding = ItemMethodBinding.bind(view)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_method, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvMethod.text = methodsList[position]
    }

    override fun getItemCount() = methodsList.size
}