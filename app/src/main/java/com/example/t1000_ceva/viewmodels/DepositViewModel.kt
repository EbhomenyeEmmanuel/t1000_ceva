package com.example.t1000_ceva.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class DepositFundState {
    object Loading : DepositFundState()
    object Success : DepositFundState()
    class Error(val exception: Exception) : DepositFundState()
}

@HiltViewModel
class DepositViewModel @Inject constructor() : ViewModel() {

    private lateinit var depositFundJob: Job

    private val appChannel = Channel<DepositFundState>(Channel.BUFFERED)
    val fetchDepositFundState = appChannel.receiveAsFlow()

    fun depositFund(depositAmount: String, attendantPin: String) {

        if (this::depositFundJob.isInitialized && depositFundJob.isActive) {
            return
        }

        depositFundJob = viewModelScope.launch {
            appChannel.send(DepositFundState.Loading)
            try {
                delay(2L)
                appChannel.send(DepositFundState.Success)
            } catch (e: Exception) {
                appChannel.send(DepositFundState.Error(Exception()))
            }
        }
    }

}