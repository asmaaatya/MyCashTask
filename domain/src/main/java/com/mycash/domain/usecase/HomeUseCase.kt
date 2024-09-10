package com.mycash.domain.usecase

import com.mycash.domain.repo.HomeRepository
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val homeRepository: HomeRepository) {
    suspend fun getHomeBaseCategories() = homeRepository.getHomeBaseCategories()
    suspend fun getPopularSellers(
        latitude: Double,
        longitude: Double,
        filter: Int
    ) = homeRepository.getPopularSellers(latitude, longitude, filter)

    suspend fun getTrendingSellers(
        latitude: Double,
        longitude: Double,
        filter: Int
    ) = homeRepository.getTrendingSellers(latitude, longitude, filter)

}