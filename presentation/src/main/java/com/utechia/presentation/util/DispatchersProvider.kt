package com.utechia.presentation.util

import kotlinx.coroutines.CoroutineDispatcher


interface DispatchersProvider {
    fun getIO(): CoroutineDispatcher
    fun getMain(): CoroutineDispatcher
    fun getDefault(): CoroutineDispatcher
}