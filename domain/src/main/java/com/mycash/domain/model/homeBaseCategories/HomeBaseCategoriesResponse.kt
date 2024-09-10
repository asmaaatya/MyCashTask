package com.mycash.domain.model.homeBaseCategories

data class HomeBaseCategoriesResponse(
    val `data`: Data,
    val message: String,
    val response_code: Int,
    val success: Boolean
)