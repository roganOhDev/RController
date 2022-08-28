
import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\b\u0010\t\u001a\u00020\u0006H\u0002J,\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u000bj\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b`\f2\u0006\u0010\r\u001a\u00020\bH\u0002J\u0006\u0010\u000e\u001a\u00020\u0004J\u0006\u0010\u000f\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000"}, d2 = {"LHttpServerHandler;", "", "()V", "server", "Lcom/sun/net/httpserver/HttpServer;", "createContext", "", "path", "", "createServer", "getQueryParameters", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "query", "getServer", "start", "Companion"})
public final class HttpServerHandler {
    @org.jetbrains.annotations.NotNull()
    public static final HttpServerHandler.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DEFAULT_HOST = "0.0.0.0";
    public static final int DEFAULT_PORT = 8080;
    public static final int DEFAULT_BACKLOG = 0;
    private com.sun.net.httpserver.HttpServer server;
    
    public HttpServerHandler() {
        super();
    }
    
    private final void createServer() {
    }
    
    public final void createContext(@org.jetbrains.annotations.NotNull()
    java.lang.String path) {
    }
    
    public final void start() {
    }
    
    private final java.util.HashMap<java.lang.String, java.lang.String> getQueryParameters(java.lang.String query) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.sun.net.httpserver.HttpServer getServer() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000"}, d2 = {"LHttpServerHandler$Companion;", "", "()V", "DEFAULT_BACKLOG", "", "DEFAULT_HOST", "", "DEFAULT_PORT"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}