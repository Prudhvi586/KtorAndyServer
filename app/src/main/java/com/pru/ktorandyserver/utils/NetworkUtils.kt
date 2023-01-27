package com.pru.ktorandyserver.utils

import java.net.InetAddress
import java.net.NetworkInterface

object NetworkUtils {

    const val port = 5001

    fun getLocalIpAddress(): String? = getInetAddresses()
        .filter { it.isLocalAddress() }
        .map { it.hostAddress }
        .firstOrNull()

    private fun getInetAddresses() = NetworkInterface.getNetworkInterfaces()
        .iterator()
        .asSequence()
        .flatMap { networkInterface ->
            networkInterface.inetAddresses
                .asSequence()
                .filter { !it.isLoopbackAddress }
        }.toList()

    private fun InetAddress.isLocalAddress(): Boolean {
        try {
            return isSiteLocalAddress
                    && !hostAddress!!.contains(":")
                    && hostAddress != "127.0.0.1"
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }
}