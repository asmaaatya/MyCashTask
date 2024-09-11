package com.mycash.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycash.domain.models.ResultApiCall
import com.mycash.domain.models.requests.HomeRequest
import com.mycash.domain.models.responses.HomeBaseCategoriesResponse
import com.mycash.domain.models.responses.PopularSellersResponse
import com.mycash.domain.models.responses.TrendingSellersResponse
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
    fun fetchPopularSellers(request: HomeRequest) {
        viewModelScope.launch {
            val result = homeUseCase.getPopularSellers(request)
            _homePopularSellers.value=result
        }
    }
    private val _homeTrendingSellers = MutableLiveData<ResultApiCall<TrendingSellersResponse>>(ResultApiCall.Loading)
    val homeTrendingSellers: MutableLiveData<ResultApiCall<TrendingSellersResponse>> = _homeTrendingSellers
    fun fetchHomeTrendingSellers(request: HomeRequest) {
        viewModelScope.launch {
            val result = homeUseCase.getTrendingSellers(request)
            _homeTrendingSellers.value=result
        }
    }
}