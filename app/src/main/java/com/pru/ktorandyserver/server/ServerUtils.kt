package com.pru.ktorandyserver.server

object ServerUtils {
    enum class ApiEndPoints(val endpoint: String) {
        Root("/"),
        Users("/users")
    }
}