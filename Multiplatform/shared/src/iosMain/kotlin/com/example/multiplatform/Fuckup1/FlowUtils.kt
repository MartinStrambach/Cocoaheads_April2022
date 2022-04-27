package com.example.multiplatform.Fuckup1

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class KotlinNativeFlowWrapper<T : Any>(private val flow: Flow<T>) {
    fun subscribe(
        scope: CoroutineScope,
        onEach: (item: T) -> Unit,
        onComplete: () -> Unit,
        onThrow: (error: Throwable) -> Unit,
    ) = flow
        .onEach { onEach(it) }
        .catch { onThrow(it) }
        .onCompletion { onComplete() }
        .launchIn(scope)
}

object ScopeFactory {
    fun createMainScope() = CoroutineScope(Dispatchers.Main + SupervisorJob())
    fun createDefaultScope() = CoroutineScope(Dispatchers.Default + SupervisorJob())
}

fun <T : Any> wrapFlow(flow: Flow<T>) = KotlinNativeFlowWrapper<T>(flow)
fun cancel(scope: CoroutineScope) {
    scope.coroutineContext.cancelChildren()
}