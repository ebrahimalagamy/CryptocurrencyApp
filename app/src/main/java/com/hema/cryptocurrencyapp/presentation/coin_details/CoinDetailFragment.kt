package com.hema.cryptocurrencyapp.presentation.coin_details

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.hema.cryptocurrencyapp.databinding.FragmentCoinDetailBinding
import com.hema.cryptocurrencyapp.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinDetailFragment :
    BaseFragment<FragmentCoinDetailBinding>(FragmentCoinDetailBinding::inflate) {

    private val vm: CoinDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.sateLiveData.observe(viewLifecycleOwner) { state ->
            binding.apply {

                dataGroup.isVisible = !state.isLoading
                pbLoading.isVisible = state.isLoading
                tvError.isVisible = state.error.isNotBlank()
                tvError.text = state.error

                state.coins?.let { it ->

                    TagsChipAdapter(ctGroup, it.tag).build()
                    lvTeam.adapter = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_list_item_1,
                        it.team.map { member ->
                            member.name
                        })
                    tvCoinName.text = it.run { "$rank. $name $symbol" }
                    tvIsActive.text = if (it.isActive) "active" else "inactive"
                    tvDescription.text = it.description
                }

            }

        }
    }

}