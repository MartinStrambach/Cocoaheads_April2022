package com.example.multiplatform

import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive

class TimeRepository {

    fun seconds(): Flow<Long> = flow {
        while (currentCoroutineContext().isActive) {
            emit(epochMillis())
            delay(1000)
        }
    }
}

expect fun epochMillis(): Long