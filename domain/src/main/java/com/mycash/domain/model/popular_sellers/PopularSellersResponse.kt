package com.mycash.domain.model.popular_sellers

data class PopularSellersResponse(
    val `data`: List<PopularListItem>,
    val message: String,
    val response_code: Int,
    val success: Boolean
)