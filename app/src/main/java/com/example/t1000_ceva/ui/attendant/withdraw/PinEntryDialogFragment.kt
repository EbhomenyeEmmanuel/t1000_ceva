package com.example.t1000_ceva.ui.attendant.withdraw

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.t1000_ceva.R
import com.example.t1000_ceva.databinding.FragmentPinEntryDialogBinding
import com.example.t1000_ceva.utils.observeInLifecycle
import com.example.t1000_ceva.utils.viewBinding
import com.example.t1000_ceva.viewmodels.WithdrawFromCardState
import com.example.t1000_ceva.viewmodels.WithdrawViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach


/**
 * Class for getting pin and initializing card withdrawal transaction
 */
@AndroidEntryPoint
class PinEntryDialogFragment : Fragment(R.layout.fragment_pin_entry_dialog) {

    private val binding by viewBinding<FragmentPinEntryDialogBinding>()
    private val viewModel by viewModels<WithdrawViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCardWithdrawalPin.setOnClickListener {
            validateEditTextInput()
        }

        observeViewState()
    }

    private fun validateEditTextInput() {
        val pin = binding.cardWithdrawalPinLayout.editText?.text.toString().trim()
        if (pin.isEmpty()) {
            binding.cardWithdrawalPinLayout.error = "Can not be empty"
            return
        }
        //After pin is entered
        viewModel.startCardWithdrawalTransaction(pin)
    }


    private fun observeViewState() {
        viewModel.fetchCardWithdrawalState.onEach { uiState ->
            when (uiState) {
                is WithdrawFromCardState.Error -> {

                }
                is WithdrawFromCardState.Loading -> {

                }
                is WithdrawFromCardState.Success -> {
                }
            }
        }.observeInLifecycle(viewLifecycleOwner)
    }
}