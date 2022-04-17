package com.example.t1000_ceva.ui.attendant.withdraw

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.example.t1000_ceva.R
import com.example.t1000_ceva.databinding.FragmentCashWithdrawalBinding
import com.example.t1000_ceva.databinding.FragmentConfirmAccountNumberBinding
import com.example.t1000_ceva.domain.model.AttendantDestination
import com.example.t1000_ceva.utils.navController
import com.example.t1000_ceva.utils.observeInLifecycle
import com.example.t1000_ceva.utils.viewBinding
import com.example.t1000_ceva.viewmodels.AttendantViewModel
import com.example.t1000_ceva.viewmodels.GenerateOTPState
import com.example.t1000_ceva.viewmodels.VerifyUserAccountNumberState
import com.example.t1000_ceva.viewmodels.WithdrawViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class CashWithdrawalFragment : Fragment(R.layout.fragment_cash_withdrawal) {
    private val binding by viewBinding<FragmentCashWithdrawalBinding>()
    private val viewModel: WithdrawViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGenerateOtp.setOnClickListener {
            generateOTP()
        }

        observeViewState()
    }

    private fun generateOTP() {
        val amount = binding.edlWithdrawalAmount.editText?.text.toString().trim()
        if (amount.isEmpty()) {
            binding.edlWithdrawalAmount.error = "Can not be empty"
            return
        }
        viewModel.generateOTP()
    }

    private fun observeViewState() {
        viewModel.fetchGenerateOTPState.onEach { uiState ->
            when (uiState) {
                is GenerateOTPState.Error -> {

                }
                is GenerateOTPState.Loading -> {

                }
                is GenerateOTPState.Success -> {
                    navController.navigate(R.id.action_cashWithdrawalFragment_to_OTPFragment)
                }
            }
        }.observeInLifecycle(viewLifecycleOwner)
    }

}