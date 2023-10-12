package com.utechia.domain.socket

interface SocketOperation {

    fun connect(token: String, onFailed: (exception: Exception) -> Unit)

    fun disconnect()

    fun listenEvent(event: Event, onData: (data: Any) -> Unit)
}