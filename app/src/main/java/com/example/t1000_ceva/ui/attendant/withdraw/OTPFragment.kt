package com.example.t1000_ceva.ui.attendant.withdraw

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.example.t1000_ceva.R
import com.example.t1000_ceva.databinding.FragmentOTPBinding
import com.example.t1000_ceva.utils.observeInLifecycle
import com.example.t1000_ceva.utils.viewBinding
import com.example.t1000_ceva.viewmodels.WithdrawCashState
import com.example.t1000_ceva.viewmodels.WithdrawViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class OTPFragment : Fragment(R.layout.fragment_o_t_p) {
    private val binding by viewBinding<FragmentOTPBinding>()
    private val viewModel: WithdrawViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCashWithdrawal.setOnClickListener {
            withdrawCash()
        }

        observeViewState()
    }

    private fun withdrawCash() {
        val otp = binding.edlOtp.editText?.text.toString().trim()
        if (otp.isEmpty()) {
            binding.edlOtp.error = "Can not be empty"
            return
        }

        val pin = binding.edlAttendantPin.editText?.text.toString().trim()
        if (pin.isEmpty()) {
            binding.edlAttendantPin.error = "Can not be empty"
            return
        }

        viewModel.withdrawCash(otp, pin)
    }

    private fun observeViewState() {
        viewModel.fetchCashWithdrawalState.onEach { uiState ->
            when (uiState) {
                is WithdrawCashState.Error -> {

                }
                is WithdrawCashState.Loading -> {

                }
                is WithdrawCashState.Success -> {
                }
            }
        }.observeInLifecycle(viewLifecycleOwner)
    }

}