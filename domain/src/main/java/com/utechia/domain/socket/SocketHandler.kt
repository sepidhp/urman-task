package com.utechia.domain.socket

import io.socket.client.Manager
import io.socket.client.Socket
import io.socket.engineio.client.Transport
import java.util.Arrays
import javax.inject.Inject

class SocketHandler @Inject constructor(
    private val socket: Socket
) : SocketOperation {

    override fun connect(token: String, onFailed: (exception: Exception) -> Unit) {
        try {
            // add auth header
            socket.on(Manager.EVENT_TRANSPORT) { args ->
                val transport = args[0] as Transport
                transport.on(Transport.EVENT_REQUEST_HEADERS) { headerArgs ->
                    val headers = headerArgs[0] as MutableMap<String, List<String>>
                    headers["Authorization"] = listOf("Bearer $token")
                }
            }
            socket.on(Socket.EVENT_CONNECT) { args ->
                println("socket connected")
            }
            socket.on(Socket.EVENT_CONNECT_ERROR) { args ->
                onFailed.invoke(Exception("Socket connect error : ${args[0]}"))
            }
            socket.on(Socket.EVENT_DISCONNECT) { args ->
                println("socket disconnected")
            }
            socket.connect()
        } catch (e: Exception) {
            e.printStackTrace()
            onFailed.invoke(e)
        }
    }

    override fun disconnect() {
        try {
            socket.disconnect()
            socket.off()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun listenEvent(event: Event, onData: (data: Any) -> Unit) {
        socket.on(event.value) { args ->
            args[0]?.let {
                onData.invoke(it)
            }
        }
    }
}