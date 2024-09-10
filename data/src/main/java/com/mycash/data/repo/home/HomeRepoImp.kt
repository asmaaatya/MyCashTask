package com.mycash.data.repo.home

import com.mycash.data.remote.apis.ApiService
import com.mycash.domain.model.ResultApiCall
import com.mycash.domain.model.homeBaseCategories.HomeBaseCategoriesResponse
import com.mycash.domain.model.popular_sellers.PopularSellersResponse
import com.mycash.domain.model.trending_sellers.TrendingSellersResponse
import com.mycash.domain.repo.HomeRepository
import javax.inject.Inject

class HomeRepoImp @Inject constructor(private val apiService: ApiService) : HomeRepository {
    override suspend fun getHomeBaseCategories(): ResultApiCall<HomeBaseCategoriesResponse> {
        return try {
            val response = apiService.getHomeBaseCategories()
            ResultApiCall.Success(response)
        } catch (e: Exception) {
            ResultApiCall.Failure(e.message ?: "Some error occurred")
        }
    }

    override suspend fun getPopularSellers(
        latitude: Double,
        longitude: Double,
        filter: Int
    ): ResultApiCall<PopularSellersResponse> {
        return try {
            val response = apiService.getPopularSellers(latitude, longitude, filter)
            ResultApiCall.Success(response)
        } catch (e: Exception) {
            ResultApiCall.Failure(e.message ?: "Some error occurred")
        }
    }

    override suspend fun getTrendingSellers(
        latitude: Double,
        longitude: Double,
        filter: Int
    ): ResultApiCall<TrendingSellersResponse> {
        return try {
            val response = apiService.getTrendingSellers(latitude, longitude, filter)
            ResultApiCall.Success(response)
        } catch (e: Exception) {
            ResultApiCall.Failure(e.message ?: "Some error occurred")
        }
    }
}
