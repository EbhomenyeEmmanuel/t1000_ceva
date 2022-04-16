package com.example.t1000_ceva.ui.attendant.airtime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import com.example.t1000_ceva.R
import com.example.t1000_ceva.commons.navController
import com.example.t1000_ceva.commons.viewBinding
import com.example.t1000_ceva.databinding.FragmentAirtimePurchaseBinding
import com.example.t1000_ceva.domain.AirtimeOperator

class AirtimePurchaseFragment : Fragment(R.layout.fragment_airtime_purchase) {
    private val binding by viewBinding<FragmentAirtimePurchaseBinding>()
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
            navController.popBackStack()
        }
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

}