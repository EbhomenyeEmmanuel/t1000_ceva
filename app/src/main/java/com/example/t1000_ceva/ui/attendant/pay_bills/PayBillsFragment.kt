package com.example.t1000_ceva.ui.attendant.pay_bills

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t1000_ceva.R
import com.example.t1000_ceva.databinding.FragmentPayBillsBinding
import com.example.t1000_ceva.ui.adapter.BillPaymentCategoryAdapter
import com.example.t1000_ceva.utils.observeInLifecycle
import com.example.t1000_ceva.utils.viewBinding
import com.example.t1000_ceva.viewmodels.BillPaymentViewModel
import com.example.t1000_ceva.viewmodels.FetchBillPaymentCateState
import kotlinx.coroutines.flow.onEach

class PayBillsFragment : Fragment(R.layout.fragment_pay_bills) {
    private val binding by viewBinding<FragmentPayBillsBinding>()
    private val viewModel: BillPaymentViewModel by viewModels()
    private lateinit var billPaymentCategoryAdapter: BillPaymentCategoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        billPaymentCategoryAdapter = BillPaymentCategoryAdapter(requireContext()) { item ->
            //Navigate to
        }
        binding.billPaymentCategoryList.layoutManager = LinearLayoutManager(requireContext())
        binding.billPaymentCategoryList.adapter = billPaymentCategoryAdapter

        observeViewState()

    }

    private fun observeViewState() {
        viewModel.fetchPaymentCategoriesState.onEach { uiState ->
            when (uiState) {
                is FetchBillPaymentCateState.Loading -> {

                }
                is FetchBillPaymentCateState.Error -> {

                }
                is FetchBillPaymentCateState.Success -> {
                    if (uiState.categoryTypes.isNotEmpty()) {
                        billPaymentCategoryAdapter.submitList(uiState.categoryTypes)
                    }
                }
            }
        }.observeInLifecycle(viewLifecycleOwner)
    }
}