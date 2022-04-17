package com.example.t1000_ceva.ui.attendant.airtime

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.t1000_ceva.R
import com.example.t1000_ceva.databinding.FragmentAirtimePurchaseBinding
import com.example.t1000_ceva.domain.AirtimeOperator
import com.example.t1000_ceva.utils.navController
import com.example.t1000_ceva.utils.observeInLifecycle
import com.example.t1000_ceva.utils.viewBinding
import com.example.t1000_ceva.viewmodels.AirtimeViewModel
import com.example.t1000_ceva.viewmodels.AirtimePurchaseState
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AirtimePurchaseFragment : Fragment(R.layout.fragment_airtime_purchase) {
    private val binding by viewBinding<FragmentAirtimePurchaseBinding>()

    private val viewModel: AirtimeViewModel by viewModels()
    var airtimeOperator: AirtimeOperator? = null

    private val airtimeOperatorList by lazy {
        arrayListOf(
            AirtimeOperator(0, "9 Mobile"),
            AirtimeOperator(1, "Airtel"),
            AirtimeOperator(2, "Glo"),
            AirtimeOperator(3, "MTN")
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeOperators()

        binding.btnPurchaseAirtime.setOnClickListener {
            validateEditInputs()
        }

        observeViewState()
    }

    private fun validateEditInputs() {
        if (airtimeOperator == null) {
            binding.edlOperator.error = "Select Operator"
            return
        } else binding.edlOperator.error = null

        val mobileNumber = binding.mobileNumberInputLayout.editText?.text.toString().trim()
        if (mobileNumber.isEmpty()) {
            binding.mobileNumberInputLayout.error = "Can not be empty"
            return
        }

        val amount = binding.airtimeAmountInputLayout.editText?.text.toString().trim()
        if (amount.isEmpty()) {
            binding.airtimeAmountInputLayout.error = "Can not be empty"
            return
        }

        val pin = binding.airtimeAttendantPinInputLayout.editText?.text.toString().trim()
        if (pin.isEmpty()) {
            binding.airtimeAttendantPinInputLayout.error = "Can not be empty"
            return
        }

        viewModel.purchaseAirtime(airtimeOperator!!, mobileNumber, amount, pin)
    }

    private fun observeOperators() {
        val airtimeList = airtimeOperatorList.map { it.name }
        val adapter =
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                airtimeList
            )
        val autoCompleteTextViewBankTv =
            binding.edlOperator.editText as? AppCompatAutoCompleteTextView
        autoCompleteTextViewBankTv?.setAdapter(adapter)
        autoCompleteTextViewBankTv?.setOnItemClickListener { _, _, position, _ ->
            airtimeOperator = airtimeOperatorList[position]
        }
    }

    private fun observeViewState() {
        viewModel.fetchAirtimePurchaseState.onEach { uiState ->
            when (uiState) {
                is AirtimePurchaseState.Error -> {

                }
                is AirtimePurchaseState.Loading -> {

                }
                is AirtimePurchaseState.Success -> {
                    navController.popBackStack()
                }
            }
        }.observeInLifecycle(viewLifecycleOwner)
    }

}