package Server;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\u0006H\u0002J\u0006\u0010\n\u001a\u00020\u0006J\u0006\u0010\u000b\u001a\u00020\u0004J\u0006\u0010\f\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000"}, d2 = {"LServer/HttpServerHandler;", "", "()V", "server", "Lcom/sun/net/httpserver/HttpServer;", "createContext", "", "path", "", "createServer", "enrollContexts", "getServer", "start", "Companion"})
public final class HttpServerHandler {
    @org.jetbrains.annotations.NotNull()
    public static final Server.HttpServerHandler.Companion Companion = null;
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
    
    private final void createContext(java.lang.String path) {
    }
    
    public final void start() {
    }
    
    public final void enrollContexts() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.sun.net.httpserver.HttpServer getServer() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000"}, d2 = {"LServer/HttpServerHandler$Companion;", "", "()V", "DEFAULT_BACKLOG", "", "DEFAULT_HOST", "", "DEFAULT_PORT"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}