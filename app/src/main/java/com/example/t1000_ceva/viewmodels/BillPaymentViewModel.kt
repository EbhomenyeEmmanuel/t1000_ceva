package com.example.t1000_ceva.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.t1000_ceva.domain.BillPaymentTypeItem
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

sealed class FetchBillPaymentUiState {
    data class Success(val categoryTypesList: List<BillPaymentTypeItem>) : FetchBillPaymentUiState()
    data class Error(val exception: Throwable) : FetchBillPaymentUiState()
}

class BillPaymentViewModel() :
    ViewModel() {

    private val _paymentCategoriesStatus : MutableStateFlow<FetchBillPaymentUiState> = MutableStateFlow(FetchBillPaymentUiState.Success(emptyList()))
    val paymentCategoriesStatus: StateFlow<FetchBillPaymentUiState> = _paymentCategoriesStatus

    init {
        getPaymentCategory()
    }

    fun getPaymentCategory(selectedItem: BillPaymentTypeItem? = null) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    delay(1L)
                    if (selectedItem == null) {
                        //call repo that gets initial data
                        _paymentCategoriesStatus.value = FetchBillPaymentUiState.Success(
                            listOf(
                                BillPaymentTypeItem(1, 1, "Betting And Lottery"),
                                BillPaymentTypeItem(2, 1, "Cable TV Bills"),
                                BillPaymentTypeItem(3, 1, "Mobile Recharge"),
                                BillPaymentTypeItem(4, 1, "School and Exam Fees"),
                                BillPaymentTypeItem(5, 1, "Toll Payments"),
                                BillPaymentTypeItem(6, 1, "Utility Bills"),
                                ) )
                    } else {
                        //Calls repo that gets data using selectedItem
                        _paymentCategoriesStatus.value = FetchBillPaymentUiState.Success(
                            with(selectedItem.copy(pageNumber = selectedItem.pageNumber + 1)){
                                listOf(
                                    BillPaymentTypeItem(1, pageNumber, "ACTV"),
                                    BillPaymentTypeItem(2, pageNumber, "DSTV"),
                                    BillPaymentTypeItem(3, pageNumber, "GoTV"),
                                    BillPaymentTypeItem(4, pageNumber, "MyTV"),
                                    BillPaymentTypeItem(5, pageNumber, "Startimes"),
                                )
                            }
                            )
                    }
                } catch (e: Throwable) {
                    Log.e( this.javaClass.simpleName,"getSelectedCategory: Error is ${e.message}")
                    _paymentCategoriesStatus.value = FetchBillPaymentUiState.Error(e)
                }
            }
        }
    }
}