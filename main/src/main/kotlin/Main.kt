import Server.HttpServerHandler

private val httpServerHandler = HttpServerHandler()

fun main(args: Array<String>) {
    httpServerHandler.start()
    httpServerHandler.enrollContexts()
//    httpServerHandler.createContext("/api/v2")
}