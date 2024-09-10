package com.mycash.domain.usecase

import com.mycash.domain.model.ResultApiCall
import com.mycash.domain.model.homeBaseCategories.HomeBaseCategoriesResponse
import com.mycash.domain.model.popular_sellers.PopularSellersResponse
import com.mycash.domain.model.trending_sellers.TrendingSellersResponse
import com.mycash.domain.repo.HomeRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeUseCaseTest {

    @Test
    fun getHomeBaseCategories() = runTest {
        //given
        val homeRepository: HomeRepository = mockk()
        val homeUseCase = HomeUseCase(homeRepository)
        val homeBaseCategoriesResponse = mockk<HomeBaseCategoriesResponse>()
        //when
        coEvery {
            homeUseCase.getHomeBaseCategories(
            )
        } coAnswers { ResultApiCall.Success(homeBaseCategoriesResponse) }
        //then
        val result = homeUseCase.getHomeBaseCategories(
        )
        assertEquals(ResultApiCall.Success(homeBaseCategoriesResponse), result)
    }

    @Test
    fun getPopularSellers() = runTest {
        val lat = 0.5
        val lang = 1.5
        val filter = 1
        //given
        val homeRepository: HomeRepository = mockk()
        val homeUseCase = HomeUseCase(homeRepository)
        val popularSellersResponse = mockk<PopularSellersResponse>()
        //when
        coEvery {
            homeUseCase.getPopularSellers(
                lat, lang, filter
            )
        } coAnswers { ResultApiCall.Success(popularSellersResponse) }
        //then
        val result = homeUseCase.getPopularSellers(lat, lang, filter)
        assertEquals(ResultApiCall.Success(popularSellersResponse), result)
    }

    @Test
    fun getTrendingSellers() = runTest {
        val lat = 0.5
        val lang = 1.5
        val filter = 1
        //given
        val homeRepository: HomeRepository = mockk()
        val homeUseCase = HomeUseCase(homeRepository)
        val trendingSellersResponse = mockk<TrendingSellersResponse>()
        //when
        coEvery {
            homeUseCase.getTrendingSellers(
                lat, lang, filter
            )
        } coAnswers { ResultApiCall.Success(trendingSellersResponse) }
        //then
        val result = homeUseCase.getTrendingSellers(lat, lang, filter)
        assertEquals(ResultApiCall.Success(trendingSellersResponse), result)
    }
}