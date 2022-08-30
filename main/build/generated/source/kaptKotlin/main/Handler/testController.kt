package Handler

import kotlin.Any

class TestController {
  fun getMeth(): Any {
    val clazz = Controller.TestController()
    return clazz.getMeth()
  }

  fun getMethod(requestParams: Any): Any {
    val clazz = Controller.TestController()
    return clazz.getMethod(requestParams)
  }
}
