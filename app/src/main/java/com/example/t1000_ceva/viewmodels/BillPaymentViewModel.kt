package com.example.t1000_ceva.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.t1000_ceva.model.domain.BillPaymentTypeItem
import com.example.t1000_ceva.repositoryImp.MainRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow

sealed class FetchBillPaymentCateState {
    object Loading : FetchBillPaymentCateState()
    class Success(val categoryTypes: List<BillPaymentTypeItem>) : FetchBillPaymentCateState()
    object Error : FetchBillPaymentCateState()
}

class BillPaymentViewModel() :
    ViewModel() {

    private val appChannel = Channel<FetchBillPaymentCateState>(Channel.BUFFERED)
    val fetchPaymentCategoriesState = appChannel.receiveAsFlow()

    init {
        downloadPaymentCategoriesDetails()
    }

    private fun downloadPaymentCategoriesDetails() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                appChannel.send(FetchBillPaymentCateState.Loading)
                try {
                    //call repo
                    delay(1L)
                    appChannel.send(
                        FetchBillPaymentCateState.Success(
                            listOf(
                                BillPaymentTypeItem(0, "Betting And Lottery"),
                                BillPaymentTypeItem(1, "Cable TV Bills"),
                                BillPaymentTypeItem(2, "Mobile Recharge"),
                                BillPaymentTypeItem(3, "School and Exam Fees"),
                                BillPaymentTypeItem(4, "Toll Payments"),
                                BillPaymentTypeItem(5, "Utility Bills"),
                            )
                        )
                    )
                } catch (e: Throwable) {
                    Log.e(
                        this.javaClass.simpleName,
                        "downloadPaymentCategoriesDetails: Error is ${e.message}"
                    )
                    appChannel.send(FetchBillPaymentCateState.Error)
                }
            }
        }
    }
}