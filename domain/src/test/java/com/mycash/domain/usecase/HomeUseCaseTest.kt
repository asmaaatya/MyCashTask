package com.mycash.domain.usecase

import com.mycash.domain.TestDummyData.homeRequestData
import com.mycash.domain.models.ResultApiCall
import com.mycash.domain.models.responses.HomeBaseCategoriesResponse
import com.mycash.domain.models.responses.PopularSellersResponse
import com.mycash.domain.models.responses.TrendingSellersResponse
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
        //given
        val homeRepository: HomeRepository = mockk()
        val homeUseCase = HomeUseCase(homeRepository)
        val popularSellersResponse = mockk<PopularSellersResponse>()
        //when
        coEvery {
            homeUseCase.getPopularSellers(
                homeRequestData
            )
        } coAnswers { ResultApiCall.Success(popularSellersResponse) }
        //then
        val result = homeUseCase.getPopularSellers(homeRequestData)
        assertEquals(ResultApiCall.Success(popularSellersResponse), result)
    }

    @Test
    fun getTrendingSellers() = runTest {

        //given
        val homeRepository: HomeRepository = mockk()
        val homeUseCase = HomeUseCase(homeRepository)
        val trendingSellersResponse = mockk<TrendingSellersResponse>()
        //when
        coEvery {
            homeUseCase.getTrendingSellers(
                homeRequestData
            )
        } coAnswers { ResultApiCall.Success(trendingSellersResponse) }
        //then
        val result = homeUseCase.getTrendingSellers(homeRequestData)
        assertEquals(ResultApiCall.Success(trendingSellersResponse), result)
    }
}