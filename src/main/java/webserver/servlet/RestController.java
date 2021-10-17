package webserver.servlet;

import java.lang.annotation.*;
import java.util.Arrays;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RestController {
    String path() default "";

    enum MethodType {
        GET(GET.class), POST(POST.class), DELETE(DELETE.class), PUT(PUT.class);
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
