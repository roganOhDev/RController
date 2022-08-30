package Controller

import dto.TestDto
import exception.HttpException
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
    fun getMethod(requestParams: Any?) : TestDto {
        val request = RequestParams(requestParams as Map<String, String>)
        val name = request.requests["name"]?:"unknown"

        if (name.isBlank()) {
            throw HttpException("name required")
        }

        return TestDto(name)
    }
}