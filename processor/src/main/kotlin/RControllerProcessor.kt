import rController.RController
import exception.ProcessorException
import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.metadata.KotlinPoetMetadataPreview
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import java.io.File
import java.nio.charset.Charset
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedAnnotationTypes
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement

private val funSpecs: MutableList<FunSpec> = mutableListOf()

@KotlinPoetMetadataPreview
@AutoService(Processor::class)
@SupportedAnnotationTypes("rController.RController")
class RControllerProcessor : AbstractProcessor() {
    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment?): Boolean {
        try {
            if (roundEnv != null) {
                roundEnv.getElementsAnnotatedWith(RController::class.java)?.forEach {
                    val elementPackage = processingEnv.elementUtils.getPackageOf(it).toString()
                    val className = it.simpleName.toString()

                    if (it.kind != ElementKind.CLASS) {
                        throw  ProcessorException("RController must be used only on controller")
                    }

                    println("Processing: $elementPackage.$className")
                    generateFile(it as Element)
//                    funSpecs.add(makeMyInfoPrintClass(it as Element))
//                    funSpecs.add(generateFun(it as TypeElement))
                }
            }
        } catch (e: ProcessorException) {
            return false
        }
        return true
    }

    private fun generateFile(element: Element) {
        val testFileBuilder = generateFunction(element)

        val kaptKotlinGeneratedDir = processingEnv.options["kapt.kotlin.generated"]!!
        testFileBuilder.writeTo(File(kaptKotlinGeneratedDir))
    }

    private fun generateFunction(element: Element): FileSpec {
        val newFunction = FunSpec.builder("_" + element.simpleName.toString().lowerCaseFirst())
        val rControllerClass = element.getAnnotation(RController::class.java)
        rControllerClass.run {
            newFunction.addStatement("println(%P)", "path : ${this.path}")
        }

        val handleFunction = FunSpec.builder("handle")
        val aa = element.getAnnotation(RController::class.java)
        aa.run {
            handleFunction.addCode(
                """
                val responseBody = exchange!!.responseBody

        try {
            val sb = StringBuilder()
            sb.append("<!DOCTYPE html>")
            sb.append("<html>")
            sb.append("<head>")
            sb.append("<meta charset=\"UTF-8\">")
            sb.append("<title>Title</title>")
            sb.append("</head>")
            sb.append("<body>")
            sb.append("<ul>")
            sb.append("<li><a href=\"basic.html\">page page page</a></li>")
            sb.append("</ul>")
            sb.append("</body>")
            sb.append("</html>")

            val byteBuffer = Charset.forName("UTF-8").encode(sb.toString())
            val contentLength = byteBuffer.limit()
            val content = ByteArray(contentLength)
            byteBuffer.get(content, 0, contentLength)

            val headers = exchange.responseHeaders
            headers.add("Content-Type", "text/html;charset=UTF-8")

            exchange.sendResponseHeaders(200, contentLength.toLong())

            responseBody.write(content)
        } catch (e:Exception){
            e.printStackTrace()
        } finally {
            exchange.close()
        }
            """.trimIndent()
            )
        }


        return FileSpec.builder("Test", element.simpleName.toString().lowerCaseFirst()) // 파일이름 설정
//            .addImport(Charset::class.java)
            .addAliasedImport(Charset::class.java, "Charset")
            .addType(
                TypeSpec.classBuilder(element.simpleName.toString())
                    .addSuperinterface(HttpHandler::class)
                    .primaryConstructor(
                        FunSpec.constructorBuilder()
                            .build()
                    )
                    .addFunction(newFunction.build())
                    .addFunction(
                        handleFunction
                            .addModifiers(KModifier.OVERRIDE)
                            .addParameter("exchange", HttpExchange::class)
                            .build()
                    )
                    .build()

            )
            .build()
    }

    private fun String.lowerCaseFirst(): String {
        val arr = this.toCharArray()
        arr[0] = Character.toLowerCase(arr[0])
        return String(arr)
    }


}