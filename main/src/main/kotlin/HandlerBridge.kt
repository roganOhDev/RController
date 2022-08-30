import com.google.gson.GsonBuilder
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import exception.HttpException
import java.nio.charset.Charset
import kotlin.Exception
import kotlin.reflect.KCallable

private val rootHandler = RootHandler()

class HandlerBridge : HttpHandler {
    override fun handle(exchange: HttpExchange?) {
        val requestBody = exchange!!.requestBody
        val requestHeaders = exchange.requestHeaders
        val requestURI = exchange.requestURI

        val responseHeaders = exchange.responseHeaders
        val responseCode = exchange.responseCode
        val responseBody = exchange.responseBody

        val endpoints = EndPoints.values().toList().associateBy { it.path }

        if (requestURI.path in listOf("/", "")) {
            rootHandler.handle(exchange)

        } else if (requestURI.path in endpoints) {
            val a = EndPoints.findByPath(requestURI.path)

            val kClass = Class.forName("Handler.${a!!.className}").kotlin
            val method = kClass.members.find { it.name == a.funName }
            val obj = kClass.constructors.toList()[0].call()

            when (exchange.requestMethod) {
                "GET" -> handleGet(method, obj, exchange)
                "POST" -> handlePost(obj, exchange)
                "PUT" -> handlePut(obj, exchange)
                "DELETE" -> handleDelete(obj, exchange)
                else -> throw HttpException("not implemented method")
            }


        } else {
            try {
                val sb = StringBuilder()
                sb.append("<!DOCTYPE html>")
                sb.append("<html>")
                sb.append("<head>")
                sb.append("<meta charset=\"UTF-8\">")
                sb.append("<title>Title</title>")
                sb.append("</head>")
                sb.append("<body>")
                sb.append("<li><a href=\"basic.html\">404 404 404</a></li>")
                sb.append("</body>")
                sb.append("</html>")

                val byteBuffer = Charset.forName("UTF-8").encode(sb.toString())
                val contentLength = byteBuffer.limit()
                val content = ByteArray(contentLength)
                byteBuffer.get(content, 0, contentLength)

                val headers = exchange.responseHeaders
                headers.add("Content-Type", "text/html;charset=UTF-8")

                exchange.sendResponseHeaders(404, contentLength.toLong())

                exchange.responseBody.write(content)
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            } finally {
                exchange.close()
            }
            println(exchange.requestURI)
            exchange.close()
        }
    }


    private fun handleGet(method: KCallable<*>?, obj: Any?, exchange: HttpExchange) {
        try {
            val response: Any?
            if (exchange.requestURI.query == null) {
                response = method!!.call(obj)
            } else {
                val query = getQueryParameters(exchange.requestURI.query)
                response = method!!.call(obj, query)
            }

            val gson = GsonBuilder().setPrettyPrinting().create()
            val gsonPretty = gson.toJson(response)

            val sb = StringBuilder()
            sb.append("<!DOCTYPE html>")
            sb.append("<html>")
            sb.append("<head>")
            sb.append("<meta charset=\"UTF-8\">")
            sb.append("<title>Title</title>")
            sb.append("</head>")
            sb.append("<body>")
            sb.append("<li><a href=\"basic.html\">$gsonPretty</a></li>")
            sb.append("</body>")
            sb.append("</html>")

            val byteBuffer = Charset.forName("UTF-8").encode(sb.toString())
            val contentLength = byteBuffer.limit()
            val content = ByteArray(contentLength)
            byteBuffer.get(content, 0, contentLength)

            val headers = exchange.responseHeaders
            headers.add("Content-Type", "text/html;charset=UTF-8")

            exchange.sendResponseHeaders(200, contentLength.toLong())

            exchange.responseBody.write(content)

            println(gsonPretty.toString())

        } catch (e: Exception) {
            val sb = StringBuilder()
            sb.append("<!DOCTYPE html>")
            sb.append("<html>")
            sb.append("<head>")
            sb.append("<meta charset=\"UTF-8\">")
            sb.append("<title>Title</title>")
            sb.append("</head>")
            sb.append("<body>")
            sb.append("<li><a href=\"basic.html\">500 500 500 : ${e.cause?.message}</a></li>")
            sb.append("</body>")
            sb.append("</html>")
            println(e.printStackTrace())

            val byteBuffer = Charset.forName("UTF-8").encode(sb.toString())
            val contentLength = byteBuffer.limit()
            val content = ByteArray(contentLength)
            byteBuffer.get(content, 0, contentLength)

            val headers = exchange.responseHeaders
            headers.add("Content-Type", "text/html;charset=UTF-8")

            exchange.sendResponseHeaders(200, contentLength.toLong())

            exchange.responseBody.write(content)

        } finally {
            exchange.close()
        }
    }

    private fun handlePost(obj: Any, exchange: HttpExchange) {

    }

    private fun handlePut(obj: Any, exchange: HttpExchange) {

    }

    private fun handleDelete(obj: Any, exchange: HttpExchange) {

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

}