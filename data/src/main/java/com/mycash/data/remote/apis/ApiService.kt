package com.mycash.data.remote.apis

import com.mycash.domain.model.homeBaseCategories.HomeBaseCategoriesResponse
import com.mycash.domain.model.login.LoginResponse
import com.mycash.domain.model.popular_sellers.PopularSellersResponse
import com.mycash.domain.model.signUp.SignUpResponse
import com.mycash.domain.model.trending_sellers.TrendingSellersResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {


    @FormUrlEncoded
    @POST("api/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @FormUrlEncoded
    @POST("api/client-register")
    suspend fun signUp(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("phone") phone: String
    ): SignUpResponse

    @GET("api/home-base-categories")
    suspend fun getHomeBaseCategories(): HomeBaseCategoriesResponse

    @GET("api/popular-sellers")
    suspend fun getPopularSellers(
        @Query("lat") latitude: Double,
        @Query("lng") longitude: Double,
        @Query("filter") filter: Int
    ): PopularSellersResponse

    @GET("api/trending-sellers")
    suspend fun getTrendingSellers(
        @Query("lat") latitude: Double,
        @Query("lng") longitude: Double,
        @Query("filter") filter: Int
    ): TrendingSellersResponse
}