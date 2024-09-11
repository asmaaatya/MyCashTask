package com.mycash.ui.home.adaters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mycash.databinding.PopularListItemBinding
import com.mycash.domain.models.beans.home.popular_sellers.PopularData
import java.math.BigDecimal
import java.math.RoundingMode

class PopularCategoriesAdapter(private var categories: List<PopularData>) :
    RecyclerView.Adapter<PopularCategoriesAdapter.PopularCategoryViewHolder>() {
    inner class PopularCategoryViewHolder(val binding: PopularListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: PopularData) {
            Glide.with(binding.image.context)
                .load(category.image)
                .into(binding.image)
            binding.nameTxt.text = category.name
            val distanceInMeters = BigDecimal(category.distance)
            val distanceInKm = distanceInMeters.divide(BigDecimal(1000), 2, RoundingMode.HALF_UP) // Specify scale and rounding mode
            binding.locationTxt.text = "${distanceInKm} Km"
            binding.rateText.text = category.rate
            binding.ratingbar.rating = category.rate.toFloat()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularCategoryViewHolder {
        val binding =
            PopularListItemBinding.inflate(LayoutInflater.from(parent.context), null, false)
        return PopularCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularCategoryViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int = categories.size
    fun updateCategories(newCategories: List<PopularData>) {
        categories = newCategories
        notifyDataSetChanged()
    }
}