package com.mycash.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mycash.databinding.FragmentHomeBinding
import com.mycash.domain.model.ResultApiCall
import com.mycash.ui.SharedViewModel
import com.mycash.ui.home.adaters.HomeCategoriesAdapter
import com.mycash.ui.home.adaters.PopularCategoriesAdapter
import com.mycash.ui.home.adaters.TrendingCategoriesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var categoriesAdapter: HomeCategoriesAdapter
    private lateinit var trendingAdapter: TrendingCategoriesAdapter
    private lateinit var popularCategoriesAdapter: PopularCategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        getUserData()
        setCategoriesList()
        return binding.root
    }

    private fun getUserData() {
        lifecycleScope.launch {
            sharedViewModel.userData.collect { data ->
                data?.let {
                    binding.welcomeTv.text = "Hello, ${it.name}"
                    binding.userLocation.text =
                        (it.addresses.firstOrNull()?.address ?: "No address available").toString()
                    val address = it.addresses.firstOrNull()
                    val lat = address?.lat?.toDoubleOrNull() ?: 0.0
                    val lng = address?.lng?.toDoubleOrNull() ?: 0.0
                    val filter = 1
                    setTrendingList(lat, lng, filter)
                    setPopularList(lat, lng, filter)
                }
            }
        }
    }

    private fun setCategoriesList() {
        categoriesAdapter = HomeCategoriesAdapter(listOf())
        binding.categoriesRv.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = categoriesAdapter
        }

            homeViewModel.homeBaseCategories.observe(viewLifecycleOwner, Observer  { result ->
                when (result) {
                    is ResultApiCall.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is ResultApiCall.Success -> {
                        binding.progressBar.visibility = View.GONE
                        categoriesAdapter.updateCategories(result.data.data.data)
                    }

                    is ResultApiCall.Failure -> {
                        binding.progressBar.visibility = View.GONE

                        Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                    }
                }
                });

        homeViewModel.fetchHomeBaseCategories()
    }

    private fun setTrendingList(lat: Double, lng: Double, filter: Int) {
        trendingAdapter = TrendingCategoriesAdapter(listOf())
        binding.trendingRv.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = trendingAdapter
        }

        homeViewModel.homeTrendingSellers.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is ResultApiCall.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is ResultApiCall.Success -> {
                    binding.progressBar.visibility = View.GONE
                    trendingAdapter.updateCategories(result.data.data)
                }

                is ResultApiCall.Failure -> {
                    binding.progressBar.visibility = View.GONE

                    Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                }
            }
        });

        homeViewModel.fetchHomeTrendingSellers(lat, lng, filter)
    }

    private fun setPopularList(lat: Double, lng: Double, filter: Int) {
        popularCategoriesAdapter = PopularCategoriesAdapter(listOf())
        binding.popularNowRv.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = popularCategoriesAdapter
        }

        homeViewModel.homePopularSellers.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is ResultApiCall.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is ResultApiCall.Success -> {
                    binding.progressBar.visibility = View.GONE
                    popularCategoriesAdapter.updateCategories(result.data.data)
                }

                is ResultApiCall.Failure -> {
                    binding.progressBar.visibility = View.GONE

                    Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                }
            }
        });


        homeViewModel.fetchPopularSellers(lat, lng, filter)
    }
}