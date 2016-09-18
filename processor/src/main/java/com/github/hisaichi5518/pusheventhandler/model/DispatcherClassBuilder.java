package com.github.hisaichi5518.pusheventhandler.model;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;

public class DispatcherClassBuilder {
    private final Logger logger;

    public DispatcherClassBuilder(Logger logger) {
        this.logger = logger;
    }

    public TypeSpec build(Element element, ProcessingEnvironment processingEnvironment) {

        String handlerPackageName = processingEnvironment.getElementUtils().getPackageOf(element).getQualifiedName().toString();
        ClassName handlerClass = ClassName.get(handlerPackageName, element.getSimpleName().toString());

        FieldSpec handlerFieldSpec = buildHandlerFieldSpec(handlerClass);

        return TypeSpec.classBuilder(element.getSimpleName() + "_Dispatcher")
                .addModifiers(Modifier.PUBLIC)
                .addField(handlerFieldSpec)
                .addMethod(new ConstructorMethodBuilder(logger).build(handlerFieldSpec, handlerClass))
                .addMethod(new DispatchMethodBuilder(logger).build(element))
                .build();
    }

    private FieldSpec buildHandlerFieldSpec(ClassName className) {
        return FieldSpec.builder(
                className, "handler", Modifier.PRIVATE, Modifier.FINAL).build();
    }
}
