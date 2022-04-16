package com.example.t1000_ceva.data

import com.example.t1000_ceva.data.api.t1000CevaApi
import com.example.t1000_ceva.domain.model.TransactionRequestData
import com.example.t1000_ceva.domain.repositories.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val api: t1000CevaApi,
) : MainRepository {

    override suspend fun downloadUserDetails(transactionRequestData: TransactionRequestData) {

    }
}