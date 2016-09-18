package com.github.hisaichi5518.pusheventhandler.model;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;

import javax.lang.model.element.Modifier;

class ConstructorMethodBuilder {
    private final Logger logger;

    ConstructorMethodBuilder(Logger logger) {
        this.logger = logger;
    }

    MethodSpec build(FieldSpec handlerFieldSpec, ClassName handlerClass) {
        return MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(ParameterSpec.builder(handlerClass, "handler").build())
                .addStatement("this.$N = handler", handlerFieldSpec)
                .build();
    }
}
