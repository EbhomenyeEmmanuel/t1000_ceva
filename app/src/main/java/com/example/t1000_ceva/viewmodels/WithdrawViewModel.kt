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

sealed class GenerateOTPState {
    object Loading : GenerateOTPState()
    object Success : GenerateOTPState()
    class Error(val exception: Exception) : GenerateOTPState()
}

sealed class WithdrawCashState {
    object Loading : WithdrawCashState()
    object Success : WithdrawCashState()
    class Error(val exception: Exception) : WithdrawCashState()
}


sealed class WithdrawFromCardState {
    object Loading : WithdrawFromCardState()
    object Success : WithdrawFromCardState()
    class Error(val exception: Exception) : WithdrawFromCardState()
}

@HiltViewModel
class WithdrawViewModel @Inject constructor() : ViewModel() {

    private lateinit var generateOTPJob: Job
    private lateinit var withdrawCashJob: Job
    private lateinit var withdrawFromCardJob: Job


    private val appChannelForOTPGeneration = Channel<GenerateOTPState>(Channel.BUFFERED)
    val fetchGenerateOTPState = appChannelForOTPGeneration.receiveAsFlow()

    private val appChannelForCashWithdrawal = Channel<WithdrawCashState>(Channel.BUFFERED)
    val fetchCashWithdrawalState = appChannelForCashWithdrawal.receiveAsFlow()

    private val appChannelFromCardWithdrawal = Channel<WithdrawFromCardState>(Channel.BUFFERED)
    val fetchCardWithdrawalState = appChannelFromCardWithdrawal.receiveAsFlow()

    fun generateOTP() {

        if (this::generateOTPJob.isInitialized && generateOTPJob.isActive) {
            return
        }

        generateOTPJob = viewModelScope.launch {
            appChannelForOTPGeneration.send(GenerateOTPState.Loading)
            try {
                delay(2L)
                appChannelForOTPGeneration.send(GenerateOTPState.Success)
            } catch (e: Exception) {
                appChannelForOTPGeneration.send(GenerateOTPState.Error(Exception()))
            }
        }
    }

    fun withdrawCash(otp: String, pin: String) {

        if (this::withdrawCashJob.isInitialized && withdrawCashJob.isActive) {
            return
        }

        withdrawCashJob = viewModelScope.launch {
            appChannelForCashWithdrawal.send(WithdrawCashState.Loading)
            try {
                delay(2L)
                appChannelForCashWithdrawal.send(WithdrawCashState.Success)
            } catch (e: Exception) {
                appChannelForCashWithdrawal.send(WithdrawCashState.Error(Exception()))
            }
        }
    }

    fun startCardWithdrawalTransaction(pin: String) {
        if (this::withdrawFromCardJob.isInitialized && withdrawFromCardJob.isActive) {
            return
        }

        withdrawFromCardJob = viewModelScope.launch {
            appChannelFromCardWithdrawal.send(WithdrawFromCardState.Loading)
            try {
                delay(2L)
                appChannelFromCardWithdrawal.send(WithdrawFromCardState.Success)
            } catch (e: Exception) {
                appChannelFromCardWithdrawal.send(WithdrawFromCardState.Error(Exception()))
            }
        }
    }

}