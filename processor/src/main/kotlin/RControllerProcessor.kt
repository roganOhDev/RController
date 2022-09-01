import rController.RController
import exception.ProcessorException
import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.metadata.KotlinPoetMetadataPreview
import rController.RGet
import java.io.File
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedAnnotationTypes
import javax.lang.model.element.*

@KotlinPoetMetadataPreview
@AutoService(Processor::class)
@SupportedAnnotationTypes("rController.RController")
class RControllerProcessor : AbstractProcessor() {
    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment?): Boolean {
        try {
            if (roundEnv != null) {

                val funElementHashMap = HashMap<String, MutableList<Element>>()
                val classElementHashMap = HashMap<String, ElementPair>()  // className, class , fun

                roundEnv.getElementsAnnotatedWith(RGet::class.java)?.forEach {
                    if (it.kind != ElementKind.METHOD) {
                        throw  ProcessorException("RGet must be used only on method")
                    }

                    val className = it.getAnnotation(RGet::class.java).className

                    if (funElementHashMap[className] == null) {
                        funElementHashMap[className] = mutableListOf()
                    }

                    funElementHashMap[className]!!.add(it)
                    //TODO : 느낌표

                }

                roundEnv.getElementsAnnotatedWith(RController::class.java)?.forEach {
                    if (it.kind != ElementKind.CLASS) {
                        throw  ProcessorException("RController must be used only on controller")
                    }

                    val className = it.simpleName.toString()

                    if (classElementHashMap[className] == null) {
                        classElementHashMap[className] = ElementPair(classElement = it)
                    }

                    if (funElementHashMap[className] != null) {
                        classElementHashMap[className]!!.funElements.addAll(funElementHashMap[className]!!)
                    }
                }

                classElementHashMap.forEach { (_, funElements) ->
                    generateFile(funElements.classElement, funElements.funElements.toList())
                }

            }
        } catch (e: ProcessorException) {
            return false
        }

        return true
    }

    private fun generateFile(classElement: Element, funElements: List<Element>) {
        val fileBuilder = generateFunction(classElement, funElements)

        val kaptKotlinGeneratedDir = processingEnv.options["kapt.kotlin.generated"]!!
        fileBuilder.writeTo(File(kaptKotlinGeneratedDir))
    }

    private fun generateFunction(classElement: Element, funElements: List<Element>): FileSpec {
        val fileBuilder = FileSpec.builder("Handler", classElement.simpleName.toString().lowerCaseFirst())

        val type = addType(classElement, funElements)

        return fileBuilder.addType(type).build()

    }

    private fun addType(classElement: Element, funElements: List<Element>): TypeSpec {
        val type = TypeSpec.classBuilder(classElement.simpleName.toString())
            .primaryConstructor(
                FunSpec.constructorBuilder()
                    .build()
            )

        val functions = addFunctions(classElement, funElements)

        return type.addFunctions(functions).build()
    }

    private fun addFunctions(classElement: Element, funElements: List<Element>): List<FunSpec> {
        val response = mutableListOf<FunSpec>()

        for (funElement in funElements) {
            val parameterList =  (funElement as ExecutableElement).parameters.map {
                it -> it.simpleName.toString()
            }.joinToString { it }
            val functionBuilder = FunSpec.builder(funElement.simpleName.toString())
                .addCode("""
                    val clazz = ${classElement.getAnnotation(RController::class.java).packagePath}.${classElement.simpleName}()
                    return clazz.${funElement.simpleName}($parameterList)""".trimIndent())
//                .returns((funElement as ExecutableElement).returnType::class.java)
                .returns(ANY)

            val parameters = addParameters(funElement)

            val function = functionBuilder.addParameters(parameters).build()
            response.add(function)
        }

        return response
    }

    private fun addParameters(funElement: Element): List<ParameterSpec> {
        val response = mutableListOf<ParameterSpec>()
        (funElement as ExecutableElement).parameters.forEach{
            parameter -> response.add(ParameterSpec.builder(parameter.simpleName.toString(), Any::class).build())
        }

        return response
    }

    private fun String.lowerCaseFirst(): String {
        val arr = this.toCharArray()
        arr[0] = Character.toLowerCase(arr[0])
        return String(arr)
    }


}