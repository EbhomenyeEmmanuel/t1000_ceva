package com.example.t1000_ceva.ui.attendant.withdraw

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.t1000_ceva.R
import com.example.t1000_ceva.databinding.FragmentCardWithdrawalBinding
import com.example.t1000_ceva.utils.navController
import com.example.t1000_ceva.utils.viewBinding
import com.example.t1000_ceva.viewmodels.WithdrawViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardWithdrawalFragment : Fragment(R.layout.fragment_card_withdrawal) {

    private val binding by viewBinding<FragmentCardWithdrawalBinding>()
    private val viewModel: WithdrawViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnWithdrawal.setOnClickListener {
            validateEditText()
        }

    }

    private fun validateEditText() {

        val amount = binding.edlWithdrawalAmount.editText?.text.toString().trim()
        if (amount.isEmpty()) {
            binding.edlWithdrawalAmount.error = "Can not be empty"
            return
        }

        navController.navigate(R.id.action_cardWithdrawalFragment_to_cardInputFragment)
    }
}