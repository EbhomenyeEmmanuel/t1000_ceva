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

sealed class VerifyUserAccountNumberState {
    object Loading : VerifyUserAccountNumberState()
    object Success : VerifyUserAccountNumberState()
    class Error(val exception: Exception) : VerifyUserAccountNumberState()
}

@HiltViewModel
class AttendantViewModel @Inject constructor() : ViewModel() {

    private lateinit var verifyUserAccountNumberJob: Job

    private val appChannel = Channel<VerifyUserAccountNumberState>(Channel.BUFFERED)
    val fetchAccountNumberState = appChannel.receiveAsFlow()

    fun confirmAccountNumber(accountNumber: String) {

        if (this::verifyUserAccountNumberJob.isInitialized && verifyUserAccountNumberJob.isActive) {
            return
        }

        verifyUserAccountNumberJob = viewModelScope.launch {
            appChannel.send(VerifyUserAccountNumberState.Loading)
            try {
                delay(2L)
                appChannel.send(VerifyUserAccountNumberState.Success)
            } catch (e: Exception) {
                appChannel.send(VerifyUserAccountNumberState.Error(Exception()))
            }
        }
    }

    fun depositFund(depositAmount: String, attendantPin: String) {
        kotlin.runCatching { }.onSuccess { }.onFailure { }
    }
}