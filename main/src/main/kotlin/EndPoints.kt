enum class EndPoints(val path: String, val className: String, val funName: String) {
    TEST1("/api/v3", "TestController", "getMeth"),
    TEST2("/api/v4", "TestController", "getMethod");
//    TEST2("/api/v2", "testController2");


    companion object{
        private val map = EndPoints.values().associateBy { it.path }
        fun findByPath(path: String) = map[path]
    }
}