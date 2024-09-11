package com.mycash.ui.home



import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mycash.TestDummyData.homeRequestData
import com.mycash.domain.models.ResultApiCall
import com.mycash.domain.models.responses.HomeBaseCategoriesResponse
import com.mycash.domain.models.responses.PopularSellersResponse
import com.mycash.domain.models.responses.TrendingSellersResponse
import com.mycash.domain.usecase.HomeUseCase
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    @Test
    fun `fetchHomeBaseCategories should update homeBaseCategories with Success result`() = runTest {
        val useCase: HomeUseCase = mockk()
        val viewModel = HomeViewModel(useCase)
        val baseCategoriesResponse = mockk<HomeBaseCategoriesResponse>()
        coEvery { useCase.getHomeBaseCategories() } returns ResultApiCall.Success(baseCategoriesResponse)
        val observer = mockk<Observer<ResultApiCall<HomeBaseCategoriesResponse>>>(relaxed = true)
        viewModel.homeBaseCategories.observeForever(observer)
        viewModel.fetchHomeBaseCategories()
        advanceUntilIdle()
        verify { observer.onChanged(ResultApiCall.Loading) }
        advanceUntilIdle()
        verify { observer.onChanged(ResultApiCall.Success(baseCategoriesResponse)) }
        viewModel.homeBaseCategories.removeObserver(observer)
    }

    @Test
    fun `fetchPopularSellers should update homePopularSellers with Success result`() = runTest {
        val useCase: HomeUseCase = mockk()
        val viewModel = HomeViewModel(useCase)
        val popularSellersResponse = mockk<PopularSellersResponse>()
        coEvery { useCase.getPopularSellers(homeRequestData) } returns ResultApiCall.Success(popularSellersResponse)
        val observer = mockk<Observer<ResultApiCall<PopularSellersResponse>>>(relaxed = true)
        viewModel.homePopularSellers.observeForever(observer)
        viewModel.fetchPopularSellers(homeRequestData)
        advanceUntilIdle()
        verify { observer.onChanged(ResultApiCall.Loading) }
        advanceUntilIdle()
        verify { observer.onChanged(ResultApiCall.Success(popularSellersResponse)) }
        viewModel.homePopularSellers.removeObserver(observer)
    }

    @Test
    fun `fetchHomeTrendingSellers should update homeTrendingSellers with Success result`() = runTest {
        val useCase: HomeUseCase = mockk()
        val viewModel = HomeViewModel(useCase)
        val trendingSellersResponse = mockk<TrendingSellersResponse>()
        coEvery { useCase.getTrendingSellers(homeRequestData) } returns ResultApiCall.Success(trendingSellersResponse)
        val observer = mockk<Observer<ResultApiCall<TrendingSellersResponse>>>(relaxed = true)
        viewModel.homeTrendingSellers.observeForever(observer)
        viewModel.fetchHomeTrendingSellers(homeRequestData)
        advanceUntilIdle()
        verify { observer.onChanged(ResultApiCall.Loading) }
        advanceUntilIdle()
        verify { observer.onChanged(ResultApiCall.Success(trendingSellersResponse)) }
        viewModel.homeTrendingSellers.removeObserver(observer)
    }
}