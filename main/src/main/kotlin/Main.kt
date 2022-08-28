private val httpServerHandler = HttpServerHandler()

fun main(args: Array<String>) {
    httpServerHandler.start()
    httpServerHandler.createContext("/api/v2")
}