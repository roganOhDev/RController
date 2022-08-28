
import java.lang.System;

@com.google.auto.service.AutoService(value = {javax.annotation.processing.Processor.class})
@com.squareup.kotlinpoet.metadata.KotlinPoetMetadataPreview()
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J$\u0010\t\u001a\u00020\n2\u0010\u0010\u000b\u001a\f\u0012\u0006\b\u0001\u0012\u00020\r\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\f\u0010\u0010\u001a\u00020\u0011*\u00020\u0011H\u0002\u00a8\u0006\u0012"}, d2 = {"LRControllerProcessor;", "Ljavax/annotation/processing/AbstractProcessor;", "()V", "generateFile", "", "element", "Ljavax/lang/model/element/Element;", "generateFunction", "Lcom/squareup/kotlinpoet/FileSpec;", "process", "", "annotations", "", "Ljavax/lang/model/element/TypeElement;", "roundEnv", "Ljavax/annotation/processing/RoundEnvironment;", "lowerCaseFirst", "", "processor"})
@javax.annotation.processing.SupportedAnnotationTypes(value = {"rController.RController"})
public final class RControllerProcessor extends javax.annotation.processing.AbstractProcessor {
    
    public RControllerProcessor() {
        super();
    }
    
    @java.lang.Override()
    public boolean process(@org.jetbrains.annotations.Nullable()
    java.util.Set<? extends javax.lang.model.element.TypeElement> annotations, @org.jetbrains.annotations.Nullable()
    javax.annotation.processing.RoundEnvironment roundEnv) {
        return false;
    }
    
    private final void generateFile(javax.lang.model.element.Element element) {
    }
    
    private final com.squareup.kotlinpoet.FileSpec generateFunction(javax.lang.model.element.Element element) {
        return null;
    }
    
    private final java.lang.String lowerCaseFirst(java.lang.String $this$lowerCaseFirst) {
        return null;
    }
}