package Controller;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0001H\u0007"}, d2 = {"LController/TestController;", "", "()V", "getMeth", "Ldto/TestDto;", "getMethod", "requestParams"})
@rController.RController(path = "/api", packagePath = "Controller")
public final class TestController {
    
    public TestController() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @rController.RGet(path = "/v3", className = "TestController")
    public final dto.TestDto getMeth() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @rController.RGet(path = "/v4", className = "TestController")
    public final dto.TestDto getMethod(@org.jetbrains.annotations.NotNull()
    java.lang.Object requestParams) {
        return null;
    }
}