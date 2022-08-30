package Server

import EndPoints
import HandlerBridge
import com.sun.net.httpserver.HttpServer
import java.net.InetSocketAddress

class HttpServerHandler {
    companion object {
        const val DEFAULT_HOST = "0.0.0.0"
        const val DEFAULT_PORT: Int = 8080
        const val DEFAULT_BACKLOG: Int = 0
    }

    private lateinit var server: HttpServer

    private fun createServer() {
        this.server = HttpServer.create(InetSocketAddress(DEFAULT_HOST, DEFAULT_PORT), DEFAULT_BACKLOG);
        server.createContext("/", HandlerBridge())
    }

    private fun createContext(path: String) {

        var newPath = path
        if (path[0] != '/') {
            newPath = "/$path"
        }

        server.createContext(newPath, HandlerBridge())
    }

    fun start() {
        createServer()
        println("[Server] [Start]   host : $DEFAULT_HOST, port : $DEFAULT_PORT")
        server.start()
    }

    fun enrollContexts() {
        for (endpoint in EndPoints.values()) {
            createContext(endpoint.path)
        }
    }



    fun getServer() = this.server

}
