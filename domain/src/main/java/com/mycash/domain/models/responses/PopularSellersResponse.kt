package com.mycash.domain.models.responses

import com.mycash.domain.models.beans.home.popular_sellers.PopularData

data class PopularSellersResponse(val data: List<PopularData>) : BaseResponse()
