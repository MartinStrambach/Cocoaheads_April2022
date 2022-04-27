package com.example.multiplatform

import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.nativeHeap
import kotlinx.cinterop.ptr

import platform.posix.gettimeofday
import platform.posix.timeval

actual fun epochMillis(): Long = memScoped {
    val timeVal = nativeHeap.alloc<timeval>()
    gettimeofday(timeVal.ptr, null)
    (timeVal.tv_sec * 1000) + (timeVal.tv_usec / 1000)
}