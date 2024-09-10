package com.mycash.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycash.domain.model.ResultApiCall
import com.mycash.domain.model.homeBaseCategories.HomeBaseCategoriesResponse
import com.mycash.domain.model.popular_sellers.PopularSellersResponse
import com.mycash.domain.model.trending_sellers.TrendingSellersResponse
import com.mycash.domain.usecase.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase
) : ViewModel() {

    private val _homeBaseCategories = MutableLiveData<ResultApiCall<HomeBaseCategoriesResponse>>(ResultApiCall.Loading)
    val homeBaseCategories: MutableLiveData<ResultApiCall<HomeBaseCategoriesResponse>> = _homeBaseCategories
    fun fetchHomeBaseCategories() {
        viewModelScope.launch {
            val result = homeUseCase.getHomeBaseCategories()
            _homeBaseCategories.value=result
        }
    }
    private val _homePopularSellers = MutableLiveData<ResultApiCall<PopularSellersResponse>>(ResultApiCall.Loading)
    val homePopularSellers: MutableLiveData<ResultApiCall<PopularSellersResponse>> = _homePopularSellers
    fun fetchPopularSellers(latitude: Double,
                            longitude: Double,
                            filter: Int) {
        viewModelScope.launch {
            val result = homeUseCase.getPopularSellers(latitude, longitude, filter)
            _homePopularSellers.value=result
        }
    }
    private val _homeTrendingSellers = MutableLiveData<ResultApiCall<TrendingSellersResponse>>(ResultApiCall.Loading)
    val homeTrendingSellers: MutableLiveData<ResultApiCall<TrendingSellersResponse>> = _homeTrendingSellers
    fun fetchHomeTrendingSellers(latitude: Double,
                                 longitude: Double,
                                 filter: Int) {
        viewModelScope.launch {
            val result = homeUseCase.getTrendingSellers(latitude, longitude, filter)
            _homeTrendingSellers.value=result
        }
    }
}