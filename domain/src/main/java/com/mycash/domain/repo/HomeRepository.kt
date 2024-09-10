package com.mycash.domain.repo

import com.mycash.domain.model.ResultApiCall
import com.mycash.domain.model.homeBaseCategories.HomeBaseCategoriesResponse
import com.mycash.domain.model.popular_sellers.PopularSellersResponse
import com.mycash.domain.model.trending_sellers.TrendingSellersResponse

interface HomeRepository {
    suspend fun getHomeBaseCategories(): ResultApiCall<HomeBaseCategoriesResponse>
    suspend fun getPopularSellers(
        latitude: Double,
        longitude: Double,
        filter: Int
    ): ResultApiCall<PopularSellersResponse>

    suspend fun getTrendingSellers(
        latitude: Double,
        longitude: Double,
        filter: Int
    ): ResultApiCall<TrendingSellersResponse>
}
