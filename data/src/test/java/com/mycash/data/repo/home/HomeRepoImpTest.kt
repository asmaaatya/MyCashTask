package com.mycash.data.repo.home

import com.mycash.data.remote.apis.ApiService
import com.mycash.domain.model.ResultApiCall
import com.mycash.domain.model.homeBaseCategories.HomeBaseCategoriesResponse
import com.mycash.domain.model.popular_sellers.PopularSellersResponse
import com.mycash.domain.model.trending_sellers.TrendingSellersResponse
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
        val lat = 0.5
        val lang = 1.5
        val filter=1
        val popularSellersResponse = mockk<PopularSellersResponse> {
            every { success } returns true
            every { message } returns "result ok "
        }
        coEvery { apiService.getPopularSellers(lat,lang,filter) } coAnswers { popularSellersResponse}

        val result = homeRepoImp.getPopularSellers(lat,lang,filter)
        assertEquals(ResultApiCall.Success(popularSellersResponse), result)
    }

    @Test
    fun getTrendingSellers() = runTest {
        val apiService: ApiService = mockk()
        val homeRepoImp=HomeRepoImp(apiService)
        val lat = 0.5
        val lang = 1.5
        val filter=1
        val trendingSellersResponse = mockk<TrendingSellersResponse> {
            every { success } returns true
            every { message } returns "result ok "
        }
        coEvery { apiService.getTrendingSellers(lat,lang,filter) } coAnswers { trendingSellersResponse}

        val result = homeRepoImp.getTrendingSellers(lat,lang,filter)
        assertEquals(ResultApiCall.Success(trendingSellersResponse), result)
    }
}