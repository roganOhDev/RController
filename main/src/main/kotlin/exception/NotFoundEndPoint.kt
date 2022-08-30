package exception

import java.nio.charset.Charset

class NotFoundEndPoint {
    fun notFound(): Pair<ByteArray, Int> {
        val sb = StringBuilder()
        sb.append("<!DOCTYPE html>")
        sb.append("<html>")
        sb.append("<head>")
        sb.append("<meta charset=\"UTF-8\">")
        sb.append("<title>Title</title>")
        sb.append("</head>")
        sb.append("<body>")
        sb.append("<ul>")
        sb.append("<li><a href=\"basic.html\">404 404 404</a></li>")
        sb.append("</ul>")
        sb.append("</body>")
        sb.append("</html>")

        val byteBuffer = Charset.forName("UTF-8").encode(sb.toString())
        val contentLength = byteBuffer.limit()
        val content = ByteArray(contentLength)
        byteBuffer.get(content, 0, contentLength)

        return Pair(content , contentLength)

    }
}