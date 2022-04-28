package com.example.multiplatform.Fuckup3

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class SuspendFunctionManager {

    private val values = mutableListOf(1, 2, 5, 6)
    private val mutex = Mutex()

    suspend fun insert(value: Int) {
        mutex.withLock {
            values.add(value)
        }
    }
}