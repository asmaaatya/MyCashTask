package com.mycash.domain.models.requests

data class HomeRequest (val latitude: Double,
                           val longitude: Double,
                           val filter: Int)