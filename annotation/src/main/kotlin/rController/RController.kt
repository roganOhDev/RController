package rController

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class RController(
    val path : String,
    val packagePath : String
){
}