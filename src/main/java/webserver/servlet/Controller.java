package webserver.servlet;

import java.lang.annotation.*;
import java.util.Arrays;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
    String path();

    enum MethodType {
        GET(webserver.servlet.GET.class), POST(webserver.servlet.POST.class);
        private final Class<? extends Annotation> annotation;
        MethodType(final Class<? extends Annotation> annotation) {
            this.annotation = annotation;
        }

        public Class<? extends Annotation> getAnnotation() {
            return annotation;
        }

        public static MethodType findMethodType(final String methodName) {
            return Arrays.stream(values()).filter(methodType -> methodType.name().equals(methodName)).findFirst().orElseThrow(NullPointerException::new);
        }
    }
}
