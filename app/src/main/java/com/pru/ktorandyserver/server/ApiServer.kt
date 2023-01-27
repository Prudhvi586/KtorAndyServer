package com.pru.ktorandyserver.server

import android.os.Build
import com.pru.ktorandyserver.utils.NetworkUtils.port
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.websocket.*

class ApiServer {
    private var server: NettyApplicationEngine? = null
    private fun getServer(): NettyApplicationEngine {
        return if (server != null) {
            server!!
        } else embeddedServer(Netty, port, watchPaths = emptyList()) {
            install(WebSockets)
            install(CallLogging)
            install(ContentNegotiation) {
                gson {
                    setPrettyPrinting()
                    disableHtmlEscaping()
                }
            }
            install(CORS) {
                method(HttpMethod.Get)
                method(HttpMethod.Post)
                method(HttpMethod.Delete)
                anyHost()
            }
            install(Compression) {
                gzip()
            }
            routing {
                get(ServerUtils.ApiEndPoints.Root.endpoint) {
                    call.respondText(
                        text = "Hello!! You are here in ${Build.MODEL}",
                        contentType = ContentType.Text.Plain
                    )
                }
                get(ServerUtils.ApiEndPoints.Users.endpoint) {
                    call.respond(HttpStatusCode.OK, listOf("Vaishnavi", "NTR"))
                }
            }
        }
    }

    private fun resetServer() {
        server = null
    }

    fun startServer() {
        getServer().start(wait = true)
    }

    fun stopServer() {
        getServer().stop(1_000, 2_000)
        resetServer()
    }
}