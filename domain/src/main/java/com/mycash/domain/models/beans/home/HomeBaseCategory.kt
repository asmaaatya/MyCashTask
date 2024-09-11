package com.mycash.domain.models.beans.home

import com.mycash.domain.models.beans.home.homeBaseCategories.DataX

data class HomeBaseCategory(
    val cart_count: Int,
    val `data`: List<DataX>
)