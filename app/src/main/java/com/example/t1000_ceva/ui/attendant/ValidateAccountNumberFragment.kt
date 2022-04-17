package com.example.t1000_ceva.ui.attendant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.example.t1000_ceva.R
import com.example.t1000_ceva.utils.navController
import com.example.t1000_ceva.databinding.FragmentValidateAccountNumberBinding
import com.example.t1000_ceva.domain.model.AttendantDestination
import com.example.t1000_ceva.utils.observeInLifecycle
import com.example.t1000_ceva.utils.viewBinding
import com.example.t1000_ceva.viewmodels.AttendantViewModel
import com.example.t1000_ceva.viewmodels.VerifyUserAccountNumberState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ValidateAccountNumberFragment : Fragment(R.layout.fragment_validate_account_number) {

    private val binding by viewBinding<FragmentValidateAccountNumberBinding>()
    private val viewModel: AttendantViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val destination =
            arguments?.get(getString(R.string.destination_type)) as? AttendantDestination
                ?: kotlin.run {
                    navController.popBackStack()
                    return
                }

        binding.btnCheckAccount.setOnClickListener {
            validateEditText()
        }

        observeViewState(destination)
    }

    private fun validateEditText() {
        val accountNumber = binding.accountNumberInputLayout.editText?.text.toString().trim()
        if (accountNumber.isEmpty()) {
            binding.accountNumberInputLayout.error = "Can not be empty"
            return
        }
        viewModel.confirmAccountNumber(accountNumber)
    }

    private fun observeViewState(destination: AttendantDestination) {
        viewModel.fetchAccountNumberState.onEach { uiState ->
            when (uiState) {
                is VerifyUserAccountNumberState.Error -> {

                }
                is VerifyUserAccountNumberState.Loading -> {

                }
                is VerifyUserAccountNumberState.Success -> {
                    navController.navigate(
                        R.id.action_validateAccountNumberFragment_to_confirmAccountNumberFragment,
                        bundleOf(Pair(getString(R.string.destination_type), destination))
                    )
                }
            }
        }.observeInLifecycle(viewLifecycleOwner)
    }

}