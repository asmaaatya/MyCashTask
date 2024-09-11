package com.mycash.domain.repo

import com.mycash.domain.models.ResultApiCall
import com.mycash.domain.models.requests.HomeRequest
import com.mycash.domain.models.responses.HomeBaseCategoriesResponse
import com.mycash.domain.models.responses.PopularSellersResponse
import com.mycash.domain.models.responses.TrendingSellersResponse

interface HomeRepository {
    suspend fun getHomeBaseCategories(): ResultApiCall<HomeBaseCategoriesResponse>
    suspend fun getPopularSellers(
       request: HomeRequest
    ): ResultApiCall<PopularSellersResponse>

    suspend fun getTrendingSellers(
        request: HomeRequest
    ): ResultApiCall<TrendingSellersResponse>
}
