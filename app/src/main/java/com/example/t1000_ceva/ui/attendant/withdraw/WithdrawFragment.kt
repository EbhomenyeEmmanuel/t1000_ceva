package com.example.t1000_ceva.ui.attendant.withdraw

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.t1000_ceva.R
import com.example.t1000_ceva.databinding.FragmentWithdrawBinding
import com.example.t1000_ceva.domain.model.AttendantDestination
import com.example.t1000_ceva.domain.model.POSLoginTypes
import com.example.t1000_ceva.domain.model.WithdrawTypeImageTitleItem
import com.example.t1000_ceva.domain.model.WithdrawTypes
import com.example.t1000_ceva.ui.adapter.ImageTitleAdapter
import com.example.t1000_ceva.utils.navController
import com.example.t1000_ceva.utils.showSnack
import com.example.t1000_ceva.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

class WithdrawFragment : Fragment(R.layout.fragment_withdraw) {
    private val binding by viewBinding<FragmentWithdrawBinding>()

    private val withdrawTypeList by lazy {
        arrayListOf(
            WithdrawTypeImageTitleItem(
                WithdrawTypes.CASH_WITHDRAWAL,
                getString(R.string.cash_withdrawal),
                //ContextCompat.getDrawable(requireContext(), R.drawable.credit_card_payment)
            ),
            WithdrawTypeImageTitleItem(
                WithdrawTypes.CARD_WITHDRAWAL,
                getString(R.string.card_withdrawal),
                //ContextCompat.getDrawable(requireContext(), R.drawable.credit_card_payment)
            ),
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = binding.withdrawList
        list.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        list.adapter = ImageTitleAdapter(requireActivity(), withdrawTypeList) {
            when (withdrawTypeList[it].withdrawTypes) {
                WithdrawTypes.CASH_WITHDRAWAL -> {
                    navController.navigate(R.id.action_withdrawFragment_to_validateAccountNumberFragment,
                        bundleOf(Pair(getString(R.string.destination_type), AttendantDestination.CASH_WITHDRAW))
                    )
                }
                WithdrawTypes.CARD_WITHDRAWAL -> {

                }
                else -> showSnack("Option unavailable")
            }
        }
    }
}