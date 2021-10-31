package webserver.servlet;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

class ServletAnnotationUtil {
    public static String getMethodPath(final Method method, final RestController.MethodType methodType) {
        final Class<? extends Annotation> annoClass = methodType.getAnnotation();
        if (GET.class.equals(annoClass)) {
            return ((GET) method.getAnnotation(annoClass)).path();
        } else if (POST.class.equals(annoClass)) {
            return ((POST) method.getAnnotation(annoClass)).path();
        } else if (DELETE.class.equals(annoClass)) {
            return ((DELETE) method.getAnnotation(annoClass)).path();
        } else if (PUT.class.equals(annoClass)) {
            return ((PUT) method.getAnnotation(annoClass)).path();
        }
        return "";
    }

    public static String getClassPath(final Class<?> clazz) {
        return clazz.getAnnotation(RestController.class).path();
    }

    public static String getServletPath(final Method method, final RestController.MethodType methodType) {
        return String.format("/%s%s%s", Config.DEFAULT_SERVLET_PATH.value(), getClassPath(method.getDeclaringClass()), getMethodPath(method, methodType));
    }
}
