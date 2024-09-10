package com.mycash.domain.model.trending_sellers

data class TrendingSellersResponse(
    val `data`: List<TrendingItemData>,
    val message: String,
    val response_code: Int,
    val success: Boolean
)