import com.sun.net.httpserver.HttpExchange
import java.lang.Exception
import java.nio.charset.Charset

class RootHandler {
    fun handle(exchange: HttpExchange){
        val responseBody = exchange.responseBody

        try {
            val sb = StringBuilder()
            sb.append("<!DOCTYPE html>")
            sb.append("<html>")
            sb.append("<head>")
            sb.append("<meta charset=\"UTF-8\">")
            sb.append("<title>Title</title>")
            sb.append("</head>")
            sb.append("<body>")
            sb.append("<ul>")
            sb.append("<li><a href=\"basic.html\">page page page</a></li>")
            sb.append("</ul>")
            sb.append("</body>")
            sb.append("</html>")

            val byteBuffer = Charset.forName("UTF-8").encode(sb.toString())
            val contentLength = byteBuffer.limit()
            val content = ByteArray(contentLength)
            byteBuffer.get(content, 0, contentLength)

            val headers = exchange.responseHeaders
            headers.add("Content-Type", "text/html;charset=UTF-8")

            exchange.sendResponseHeaders(200, contentLength.toLong())

            responseBody.write(content)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            exchange.close()
        }
    }
}