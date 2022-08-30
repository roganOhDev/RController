package rController

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class RGet(
    val path : String,
    val className : String
){
}