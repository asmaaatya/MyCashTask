package com.mycash.data.repo.home

import com.mycash.data.remote.apis.ApiService
import com.mycash.domain.models.ResultApiCall
import com.mycash.domain.models.requests.HomeRequest
import com.mycash.domain.models.responses.HomeBaseCategoriesResponse
import com.mycash.domain.models.responses.PopularSellersResponse
import com.mycash.domain.models.responses.TrendingSellersResponse
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
        request: HomeRequest
    ): ResultApiCall<PopularSellersResponse> {
        return try {
            val response = apiService.getPopularSellers(request.latitude,request.longitude,request.filter)
            ResultApiCall.Success(response)
        } catch (e: Exception) {
            ResultApiCall.Failure(e.message ?: "Some error occurred")
        }
    }

    override suspend fun getTrendingSellers(
        request: HomeRequest
    ): ResultApiCall<TrendingSellersResponse> {
        return try {
            val response = apiService.getTrendingSellers(request.latitude,request.longitude,request.filter)
            ResultApiCall.Success(response)
        } catch (e: Exception) {
            ResultApiCall.Failure(e.message ?: "Some error occurred")
        }
    }
}
