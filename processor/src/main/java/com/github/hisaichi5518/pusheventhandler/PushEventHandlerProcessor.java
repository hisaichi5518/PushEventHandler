package com.github.hisaichi5518.pusheventhandler;

import com.github.hisaichi5518.pusheventhandler.annotation.PushEventHandler;
import com.github.hisaichi5518.pusheventhandler.model.DispatcherClassBuilder;
import com.github.hisaichi5518.pusheventhandler.model.Logger;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;

import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

@AutoService(Processor.class)
@SupportedAnnotationTypes({"com.github.hisaichi5518.pusheventhandler.annotation.PushEventHandler"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class PushEventHandlerProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Logger logger = new Logger(processingEnv.getMessager());

        for (Element element : roundEnvironment.getElementsAnnotatedWith(PushEventHandler.class)) {
            try {
                JavaFile.builder("com.github.hisaichi5518.pusheventhandler", new DispatcherClassBuilder(logger).build(element, processingEnv))
                        .build()
                        .writeTo(processingEnv.getFiler());
            } catch (IOException e) {
                logger.error("failed create pusheventhandler_dispatcher");
                e.printStackTrace();
            }
        }

        return false;
    }
}
