package com.example.t1000_ceva.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.t1000_ceva.domain.model.TransactionRequestData
import com.example.t1000_ceva.domain.model.TransactionType
import com.example.t1000_ceva.domain.repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

sealed class FetchUserState {
    object Loading : FetchUserState()
    object Success : FetchUserState()
    class Error (val exception: Exception): FetchUserState()
}

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {
    private lateinit var downloadUserDetailsJob: Job

    private val appChannel = Channel<FetchUserState>(Channel.BUFFERED)
    val fetchUserDetailsState = appChannel.receiveAsFlow()

    fun downloadUserDetails() {
        if (this::downloadUserDetailsJob.isInitialized && downloadUserDetailsJob.isActive) {
            return
        }

        downloadUserDetailsJob = viewModelScope.launch {
            val transactionRequestData = TransactionRequestData(TransactionType.USER_DOWNLOAD_DETAILS)
            appChannel.send(FetchUserState.Loading)
            withContext(Dispatchers.IO) {
                try {
                    mainRepository.downloadUserDetails(transactionRequestData)
                    delay(2L)
                    appChannel.send(FetchUserState.Success)
                } catch (e: Throwable) {
                    Log.e(this.javaClass.simpleName, "downloadUserDetails: Error is ${e.message}")
                    appChannel.send(FetchUserState.Error(Exception("")))
                }
            }
        }
    }
}