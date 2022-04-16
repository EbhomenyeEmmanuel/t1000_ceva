package com.example.t1000_ceva.domain.repositories

import com.example.t1000_ceva.domain.model.TransactionRequestData

interface MainRepository {
    suspend fun downloadUserDetails(transactionRequestData: TransactionRequestData)
}