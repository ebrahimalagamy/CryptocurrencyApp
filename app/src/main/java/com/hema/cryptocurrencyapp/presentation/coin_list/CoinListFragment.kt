package com.hema.cryptocurrencyapp.presentation.coin_list

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hema.cryptocurrencyapp.databinding.FragmentCoinListBinding
import com.hema.cryptocurrencyapp.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinListFragment
    : BaseFragment<FragmentCoinListBinding>(FragmentCoinListBinding::inflate) {

    private val vm: CoinListViewModel by viewModels()
    private val coinListAdapter = CoinListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindRecyclerView()

        coinListAdapter.navCallBack = {
            findNavController().navigate(
                CoinListFragmentDirections.actionCoinListFragmentToCoinDetailFragment(
                    it.id
                )
            )
            Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show()

        }

        vm.sateLiveData.observe(viewLifecycleOwner) { state ->
            binding.apply {
                if (state.error.isNotBlank()) tvError.text = state.error
                tvError.isVisible = state.error.isNotBlank()
                pbLoading.isVisible = state.isLoading
                    coinListAdapter.listOfCoins = state.coins
                Log.e("fdata", state.coins.toString())
            }

        }
    }

    private fun bindRecyclerView() {
        binding.apply {
            rcCoinList.apply {
                adapter = coinListAdapter
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false,
                )
            }
        }

    }

}