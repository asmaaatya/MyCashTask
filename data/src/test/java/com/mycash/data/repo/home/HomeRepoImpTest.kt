package com.mycash.data.repo.home

import com.mycash.data.TestDummyData
import com.mycash.data.TestDummyData.filter
import com.mycash.data.TestDummyData.homeRequestData
import com.mycash.data.TestDummyData.latitude
import com.mycash.data.TestDummyData.longitude
import com.mycash.data.remote.apis.ApiService
import com.mycash.domain.models.ResultApiCall
import com.mycash.domain.models.responses.HomeBaseCategoriesResponse
import com.mycash.domain.models.responses.PopularSellersResponse
import com.mycash.domain.models.responses.TrendingSellersResponse
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeRepoImpTest {

    @Test
    fun getHomeBaseCategories() = runTest {

        val apiService: ApiService = mockk()
        val homeRepoImp=HomeRepoImp(apiService)

        val baseCategoriesResponse = mockk<HomeBaseCategoriesResponse> {
            every { success } returns true
            every { message } returns "result ok "
        }
        coEvery { apiService.getHomeBaseCategories() } coAnswers { baseCategoriesResponse}

        val result = homeRepoImp.getHomeBaseCategories()
        assertEquals(ResultApiCall.Success(baseCategoriesResponse), result)
    }

    @Test
    fun getPopularSellers() = runTest {
        val apiService: ApiService = mockk()
        val homeRepoImp=HomeRepoImp(apiService)
        val popularSellersResponse = mockk<PopularSellersResponse> {
            every { success } returns true
            every { message } returns "result ok "
        }
        coEvery { apiService.getPopularSellers(latitude,longitude, filter ) } coAnswers { popularSellersResponse}

        val result = homeRepoImp.getPopularSellers(homeRequestData)
        assertEquals(ResultApiCall.Success(popularSellersResponse), result)
    }

    @Test
    fun getTrendingSellers() = runTest {
        val apiService: ApiService = mockk()
        val homeRepoImp=HomeRepoImp(apiService)
        val trendingSellersResponse = mockk<TrendingSellersResponse> {
            every { success } returns true
            every { message } returns "result ok "
        }
        coEvery { apiService.getTrendingSellers(latitude,longitude, TestDummyData.filter) } coAnswers { trendingSellersResponse}

        val result = homeRepoImp.getTrendingSellers(homeRequestData)
        assertEquals(ResultApiCall.Success(trendingSellersResponse), result)
    }
}