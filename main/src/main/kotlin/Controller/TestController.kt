package Controller

import dto.TestDto
import rController.RController
import rController.RGet
import util.RequestParams

@RController("/api", "Controller")
class TestController {
    @RGet("/v3" , "TestController")
    fun getMeth() : TestDto {
        return TestDto("abc")
    }

    @RGet("/v4" , "TestController")
    fun getMethod(requestParams: Any) : TestDto {
        val request = requestParams as RequestParams
        return TestDto("dto")
    }
}