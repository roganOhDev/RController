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
        server.createContext("/", RootHandler())
    }

    fun createContext(path: String) {

        if (path.isEmpty()) {
            server.createContext("/", RootHandler())
        } else {

            var newPath = path
            if (path[0] != '/') {
                newPath = "/$path"
            }

            server.createContext(newPath, RootHandler())
        }
    }

    fun start() {
        createServer()
        println("[Server] [Start]   host : $DEFAULT_HOST, port : $DEFAULT_PORT")
        server.start()
    }

    private fun getQueryParameters(query: String): HashMap<String, String> {
        val result = HashMap<String, String>()
        for (param: String in query.split("&")) {
            val pair = param.split("=")

            if (pair.count() > 1) {
                result[pair[0]] = pair[1]
            } else {
                result[pair[0]] = ""
            }
        }

        return result
    }

    fun getServer() = this.server

}