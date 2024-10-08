package com.mycash.ui.home.adaters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mycash.databinding.TrendingListItemBinding
import com.mycash.domain.models.beans.home.trending_sellers.TrendingData

class TrendingCategoriesAdapter (private var categories: List<TrendingData>) : RecyclerView.Adapter<TrendingCategoriesAdapter.TrendingCategoryViewHolder>() {
    inner class TrendingCategoryViewHolder(val binding: TrendingListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: TrendingData) {
            Glide.with(binding.image.context)
                .load(category.image)
                .into(binding.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingCategoryViewHolder {
        val binding =
            TrendingListItemBinding.inflate(LayoutInflater.from(parent.context), null, false)
        return TrendingCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrendingCategoryViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int = categories.size
    fun updateCategories(newCategories: List<TrendingData>) {
        categories = newCategories
        notifyDataSetChanged()
    }
}