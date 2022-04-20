package com.example.t1000_ceva.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.t1000_ceva.domain.BillPaymentTypeItem
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

sealed class FetchBillPaymentCateState {
    object Loading : FetchBillPaymentCateState()
    class Success(val categoryTypesList: List<BillPaymentTypeItem>) : FetchBillPaymentCateState()
    object Error : FetchBillPaymentCateState()
}

class BillPaymentViewModel() :
    ViewModel() {

    private val appChannel = Channel<FetchBillPaymentCateState>(Channel.BUFFERED)
    val fetchPaymentCategoriesState = appChannel.receiveAsFlow()

    init {
        getPaymentCategory()
    }

    fun getPaymentCategory(selectedItem: BillPaymentTypeItem? = null) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                appChannel.send(FetchBillPaymentCateState.Loading)
                try {
                    delay(1L)
                    if (selectedItem == null) {
                        //call repo that gets initial data
                        appChannel.send(
                            FetchBillPaymentCateState.Success(
                                listOf(
                                    BillPaymentTypeItem(0, 1, "Betting And Lottery"),
                                    BillPaymentTypeItem(1, 1, "Cable TV Bills"),
                                    BillPaymentTypeItem(2, 1, "Mobile Recharge"),
                                    BillPaymentTypeItem(3, 1, "School and Exam Fees"),
                                    BillPaymentTypeItem(4, 1, "Toll Payments"),
                                    BillPaymentTypeItem(5, 1, "Utility Bills"),
                                )
                            )
                        )
                    } else {
                        //Calls repo that gets data using selectedItem
                        appChannel.send(
                            FetchBillPaymentCateState.Success(
                                listOf(
                                    BillPaymentTypeItem(0, selectedItem.pageNumber, "ACTV"),
                                    BillPaymentTypeItem(1, selectedItem.pageNumber, "DSTV"),
                                    BillPaymentTypeItem(2, selectedItem.pageNumber, "GoTV"),
                                    BillPaymentTypeItem(3, selectedItem.pageNumber, "MyTV"),
                                    BillPaymentTypeItem(4, selectedItem.pageNumber, "Startimes"),
                                )
                            )
                        )
                    }
                } catch (e: Throwable) {
                    Log.e(
                        this.javaClass.simpleName,
                        "getSelectedCategory: Error is ${e.message}"
                    )
                    appChannel.send(FetchBillPaymentCateState.Error)
                }
            }
        }
    }
}