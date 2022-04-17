package com.example.t1000_ceva.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.t1000_ceva.domain.AirtimeOperator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class AirtimePurchaseState {
    object Loading : AirtimePurchaseState()
    object Success : AirtimePurchaseState()
    class Error(val exception: Exception) : AirtimePurchaseState()
}

@HiltViewModel
class AirtimeViewModel @Inject constructor() : ViewModel() {

    private lateinit var airtimePurchaseJob: Job

    private val appChannel = Channel<AirtimePurchaseState>(Channel.BUFFERED)
    val fetchAirtimePurchaseState = appChannel.receiveAsFlow()

    fun purchaseAirtime(
        airtimeOperator: AirtimeOperator,
        mobileNumber: String,
        amount: String,
        pin: String
    ) {

        if (this::airtimePurchaseJob.isInitialized && airtimePurchaseJob.isActive) {
            return
        }

        airtimePurchaseJob = viewModelScope.launch {
            appChannel.send(AirtimePurchaseState.Loading)
            try {
                delay(2L)
                appChannel.send(AirtimePurchaseState.Success)
            } catch (e: Exception) {
                appChannel.send(AirtimePurchaseState.Error(Exception()))
            }
        }
    }

}