package com.example.multiplatform.Fuckup3

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class SuspendFunctionWrapper(val scope: CoroutineScope) {
    val suspendFunctionManager = SuspendFunctionManager()

    fun insert(value: Int) {
        scope.launch { suspendFunctionManager.insert(value) }
    }

}