package com.mycash.domain.models.responses

import com.mycash.domain.models.beans.home.trending_sellers.TrendingData


data class TrendingSellersResponse(val data: List<TrendingData>) : BaseResponse()