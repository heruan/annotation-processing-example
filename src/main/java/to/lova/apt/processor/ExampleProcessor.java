package to.lova.apt.processor;

import java.util.Collections;
import java.util.Set;

import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import com.google.auto.service.AutoService;
import com.helger.jcodemodel.JCodeModel;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JMod;

import mbenson.annotationprocessing.CodeModelProcessorBase;

@AutoService(Processor.class)
public class ExampleProcessor extends CodeModelProcessorBase {

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_8;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(ExampleAnnotation.class.getName());
    }

    @Override
    protected boolean processTo(JCodeModel codeModel,
            Set<? extends TypeElement> annotations, RoundEnvironment roundEnv)
            throws Throwable {

        for (Element element : roundEnv
                .getElementsAnnotatedWith(ExampleAnnotation.class)) {

            JDefinedClass definedClass = codeModel
                    ._class(element.getSimpleName().toString() + "_");

            definedClass.field(JMod.PUBLIC | JMod.FINAL, String.class, "foo");

        }

        return true;
    }

}
