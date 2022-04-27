package com.example.t1000_ceva.ui.attendant.pay_bills

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t1000_ceva.R
import com.example.t1000_ceva.databinding.FragmentSecondPayBillsPageBinding
import com.example.t1000_ceva.ui.adapter.BillPaymentCategoryAdapter
import com.example.t1000_ceva.utils.navController
import com.example.t1000_ceva.utils.observeInLifecycle
import com.example.t1000_ceva.utils.viewBinding
import com.example.t1000_ceva.viewmodels.BillPaymentViewModel
import com.example.t1000_ceva.viewmodels.FetchBillPaymentUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SecondPayBillsPageFragment : Fragment(R.layout.fragment_second_pay_bills_page) {
    private val binding by viewBinding<FragmentSecondPayBillsPageBinding>()
    private val viewModel: BillPaymentViewModel by navGraphViewModels(R.id.bill_payment_stepper_nav)
    private lateinit var billPaymentCategoryAdapter: BillPaymentCategoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.pageTitle.visibility = if (item.paymentTypeName.isNotEmpty()) View.VISIBLE else View.GONE
//        binding.pageTitle.text = item.paymentTypeName
        billPaymentCategoryAdapter = BillPaymentCategoryAdapter(requireContext()) { item ->
            viewModel.getPaymentCategory(item)
            navController.navigate(R.id.action_secondPayBillsPageFragment_to_thirdPayBillsPageFragment)
        }
        binding.billPaymentCategoryList.layoutManager = LinearLayoutManager(requireContext())
        binding.billPaymentCategoryList.adapter = billPaymentCategoryAdapter
        binding.billPaymentCategoryList.itemAnimator = null

        observeViewState()

    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    private fun observeViewState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.paymentCategoriesStatus.collect { uiState ->
                    when (uiState) {
                        is FetchBillPaymentUiState.Error -> {

                        }
                        is FetchBillPaymentUiState.Success -> {
                            if (uiState.categoryTypesList.isNotEmpty()) {
                                billPaymentCategoryAdapter.submitList(uiState.categoryTypesList)
                            }
                        }
                    }
                }
            }
        }
    }
}