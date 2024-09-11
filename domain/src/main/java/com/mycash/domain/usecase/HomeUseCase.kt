package com.mycash.domain.usecase

import com.mycash.domain.models.requests.HomeRequest
import com.mycash.domain.repo.HomeRepository
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val homeRepository: HomeRepository) {
    suspend fun getHomeBaseCategories() = homeRepository.getHomeBaseCategories()
    suspend fun getPopularSellers(
        request: HomeRequest
    ) = homeRepository.getPopularSellers(request)

    suspend fun getTrendingSellers(
        request: HomeRequest
    ) = homeRepository.getTrendingSellers(request)

}