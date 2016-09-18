package com.github.hisaichi5518.pusheventhandler.model;

import com.github.hisaichi5518.pusheventhandler.annotation.OnEvent;
import com.github.hisaichi5518.pusheventhandler.entity.EventPushPayload;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.VariableElement;

class DispatchMethodBuilder {
    private final Logger logger;

    DispatchMethodBuilder(Logger logger) {
        this.logger = logger;
    }

    MethodSpec build(Element element) {
        MethodSpec.Builder builder = MethodSpec.methodBuilder("dispatch")
                .addModifiers(Modifier.PUBLIC)
                .addParameter(ParameterSpec.builder(EventPushPayload.class, "payload").build());

        for (Element enclosedElement : element.getEnclosedElements()) {
            if (!(enclosedElement instanceof ExecutableElement)) {
                logger.note("skip. not ExecutableElement");
                continue;
            }

            OnEvent onEvent = enclosedElement.getAnnotation(OnEvent.class);
            if (onEvent == null) {
                logger.note("skip. not @OnEvent");
                continue;
            }

            builder.beginControlFlow("if (payload.getEvent() == $S)", onEvent.value());

            List<? extends VariableElement> parameters = ((ExecutableElement) enclosedElement).getParameters();
            if (parameters.size() > 0) {
                VariableElement variableElement = parameters.get(0);
                builder.addStatement("this.handler.$L(($T) payload)", enclosedElement.getSimpleName(), variableElement.asType());
            } else {
                builder.addStatement("this.handler.$L()", enclosedElement.getSimpleName());
            }

            builder.addStatement("return")
                    .endControlFlow();
        }

        return builder.build();
    }
}
