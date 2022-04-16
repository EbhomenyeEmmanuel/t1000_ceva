package com.example.t1000_ceva.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.t1000_ceva.repositoryImp.MainRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

sealed class FetchUserState {
    object Loading : FetchUserState()
    object Success : FetchUserState()
    object Error : FetchUserState()
}

class MainViewModel(private val mainRepository: MainRepository = MainRepository()) : ViewModel() {
    private lateinit var downloadUserDetailsJob: Job

    private val appChannel = Channel<FetchUserState>(Channel.BUFFERED)
    val fetchUserDetailsState = appChannel.receiveAsFlow()

    fun downloadUserDetails() {
        if (this::downloadUserDetailsJob.isInitialized && downloadUserDetailsJob.isActive) {
            return
        }

        downloadUserDetailsJob = viewModelScope.launch {
            appChannel.send(FetchUserState.Loading)
            withContext(Dispatchers.IO) {
                try {
                    //call repo
                    delay(2L)
                    appChannel.send(FetchUserState.Success)
                } catch (e: Throwable) {
                    Log.e(this.javaClass.simpleName, "downloadUserDetails: Error is ${e.message}")
                    appChannel.send(FetchUserState.Error)
                }
            }
        }
    }
}