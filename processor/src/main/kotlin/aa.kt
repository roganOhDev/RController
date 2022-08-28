//class GeneratorProxyProcessor : AbstractProcessor() {
//
//    override fun getSupportedAnnotationTypes(): MutableSet<String> {
//        return mutableSetOf(GenerateProxy::class.java.name)
//    }
//
//    override fun getSupportedSourceVersion(): SourceVersion {
//        return SourceVersion.latest()
//    }
//
//    override fun process(set: MutableSet<out TypeElement>, roundEnv: RoundEnvironment): Boolean {
//        roundEnv.getElementsAnnotatedWith(GenerateProxy::class.java).forEach { element ->
//            if (!element.isInterface()) {
//                processingEnv.messager.printMessage(Diagnostic.Kind.ERROR, "Can only be applied to interface, element: $element")
//                return false
//            }
//
//            generateClass(element)
//        }
//        return true
//    }
//
//    private fun generateClass(element: Element) {
//        val destinationSource: String = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME].orEmpty()
//        if (destinationSource.isEmpty()) {
//            processingEnv.messager.printMessage(Diagnostic.Kind.ERROR, "Can't find the target directory for generated Kotlin files.")
//            return
//        }
//
//        val className = element.simpleName.toString()
//        val classPackage = processingEnv.elementUtils.getPackageOf(element).toString()
//        val classfileName = "$className$SUFFIX_ADD_TO_CLASS"
//        val classBuilder = generateClassBuilder(classfileName, element.asType().asTypeName())
//
//        ElementFilter.methodsIn(element.enclosedElements).forEach { elementMethod ->
//            val executableElement = elementMethod as ExecutableElement
//            val nameFunction = executableElement.simpleName.toString()
//
//            val function = generateFunction(
//                nameFun = nameFunction,
//                returnTypeName = elementMethod.returnType.asTypeName(),
//                parameterTypes = (executableElement.asType() as ExecutableType).parameterTypes,
//                parameters = executableElement.parameters
//            )
//
//            classBuilder.addFunction(function)
//        }
//
//        val file = FileSpec.builder(classPackage, classfileName)
//            .addType(classBuilder.build())
//            .build()
//        file.writeTo(File(destinationSource))
//    }
//
//    private fun generateClassBuilder(nameFile: String, typeName: TypeName): TypeSpec.Builder {
//
//        return TypeSpec.classBuilder(nameFile)
//            .primaryConstructor(FunSpec.constructorBuilder()
//                .addParameter(VARIABLE_NAME, typeName)
//                .build())
//            .addProperty(PropertySpec.builder(VARIABLE_NAME, typeName)
//                .mutable(false)
//                .initializer(VARIABLE_NAME)
//                .build())
//            .addSuperinterface(typeName)
//    }
//
//    private fun generateFunction(nameFun: String,
//                                 returnTypeName: TypeName,
//                                 parameterTypes: List<TypeMirror>,
//                                 parameters: List<VariableElement>): FunSpec {
//
//        return FunSpec.builder(nameFun)
//            .returns(returnTypeName.javaToKotlinType())
//            .addParameters(processParameters(parameters, parameterTypes))
//            .addModifiers(KModifier.OVERRIDE, KModifier.SUSPEND)
//            .addStatement("return " + VARIABLE_NAME + "." + nameFun + "(" +
//                    getInvokedParams(parameters, parameterTypes) + ")")
//            .build()
//    }
//
//    companion object {
//        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
//        const val SUFFIX_ADD_TO_CLASS = "Proxy"
//        const val VARIABLE_NAME = "dao"
//    }
//}