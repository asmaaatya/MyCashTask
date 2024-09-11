package com.mycash.ui.home.adaters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mycash.databinding.CategoriesListItemBinding
import com.mycash.domain.models.beans.home.homeBaseCategories.DataX

class HomeCategoriesAdapter(private var categories: List<DataX>) : RecyclerView.Adapter<HomeCategoriesAdapter.CategoryViewHolder>() {
    inner class CategoryViewHolder(val binding: CategoriesListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(category: DataX) {
            binding.categoryName.text = category.name
            Glide.with(binding.image.context)
                .load(category.image)
                .into(binding.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoriesListItemBinding.inflate(LayoutInflater.from(parent.context), null, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int = categories.size
    fun updateCategories(newCategories: List<DataX>) {
        categories = newCategories
        notifyDataSetChanged()
    }
}
