package com.mycash.data.remote.apis

import com.mycash.domain.models.responses.HomeBaseCategoriesResponse
import com.mycash.domain.models.responses.LoginResponse
import com.mycash.domain.models.responses.PopularSellersResponse
import com.mycash.domain.models.requests.LogInRequest
import com.mycash.domain.models.requests.SingUpRequest
import com.mycash.domain.models.responses.SignUpResponse
import com.mycash.domain.models.responses.TrendingSellersResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {


    @POST("api/login")
    suspend fun login(
        @Body request: LogInRequest
    ): LoginResponse

    @POST("api/client-register")
    suspend fun signUp(
        @Body request: SingUpRequest
    ): SignUpResponse

    @GET("api/home-base-categories")
    suspend fun getHomeBaseCategories(): HomeBaseCategoriesResponse


    @GET("api/popular-sellers")
    suspend fun getPopularSellers(
        //handle pass query  map
        @Query("lat") latitude: Double,
        @Query("lng") longitude: Double,
        @Query("filter") filter: Int,

    ): PopularSellersResponse

    @GET("api/trending-sellers")
    suspend fun getTrendingSellers(
        @Query("lat") latitude: Double,
        @Query("lng") longitude: Double,
        @Query("filter") filter: Int,
    ): TrendingSellersResponse

}