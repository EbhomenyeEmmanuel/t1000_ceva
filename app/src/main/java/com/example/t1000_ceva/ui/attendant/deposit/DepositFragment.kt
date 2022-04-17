package com.example.t1000_ceva.ui.attendant.deposit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.example.t1000_ceva.R
import com.example.t1000_ceva.utils.navController
import com.example.t1000_ceva.databinding.FragmentDepositBinding
import com.example.t1000_ceva.domain.model.AttendantDestination
import com.example.t1000_ceva.utils.observeInLifecycle
import com.example.t1000_ceva.utils.viewBinding
import com.example.t1000_ceva.viewmodels.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DepositFragment : Fragment(R.layout.fragment_deposit) {
    private val binding by viewBinding<FragmentDepositBinding>()
    private val viewModel: DepositViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPayForDeposit.setOnClickListener {
            validateEditText()
        }

        observeViewState()
    }

    private fun validateEditText() {
        val depositAmount = binding.depositAmountInputLayout.editText?.text.toString().trim()
        if (depositAmount.isEmpty()) {
            binding.depositAmountInputLayout.error = "Can not be empty"
            return
        }
        val attendantPin = binding.attendantPinLayout.editText?.text.toString().trim()
        if (attendantPin.isEmpty()) {
            binding.attendantPinLayout.error = "Can not be empty"
            return
        }
        viewModel.depositFund(depositAmount, attendantPin)
    }


    private fun observeViewState() {
        viewModel.fetchDepositFundState.onEach { uiState ->
            when (uiState) {
                is DepositFundState.Error -> {

                }
                is DepositFundState.Loading -> {

                }
                is DepositFundState.Success -> {

                }
            }
        }.observeInLifecycle(viewLifecycleOwner)
    }
}