package com.dicoding.mistoriyy.home

import com.dicoding.mistoriyy.data.di.StoriyResult
import android.os.Bundle
import android.view.*
import androidx.fragment.app.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mistoriyy.LoadingStateAdapter
import com.dicoding.mistoriyy.R
import com.dicoding.mistoriyy.databinding.FragmentHomeBinding
import com.dicoding.mistoriyy.storiyy.StoriyAdapter
import com.google.android.material.snackbar.Snackbar

@Suppress("KotlinConstantConditions")
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels {
        HomeViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupRv()

        homeViewModel.findStory()
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.findStory()
    }

    private fun setupRv() {
        val adapter = StoriyAdapter()
        binding.rvStoriy.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvStoriy.adapter = adapter

    }

    private fun setupObservers() {
        homeViewModel.story.observe(viewLifecycleOwner) { result ->
            when (result) {
                is StoriyResult.Loading -> binding.progressBar.visibility = View.VISIBLE
                is StoriyResult.Success -> {
                    binding.progressBar.visibility = View.GONE
                    (binding.rvStoriy.adapter as StoriyAdapter).submitList(result.data)
                }

                is StoriyResult.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Snackbar.make(
                        requireView(),
                        getString(R.string.failed_load_story), Snackbar.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}