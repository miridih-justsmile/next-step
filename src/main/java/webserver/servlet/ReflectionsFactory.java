package webserver.servlet;

import com.google.common.collect.ImmutableMap;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ReflectionsFactory {
    private final static Logger log = LoggerFactory.getLogger(ReflectionsFactory.class);
    private final static Reflections reflections = new Reflections(
            new ConfigurationBuilder()
                    .setUrls(ClasspathHelper.forPackage("webserver"))
                    .setScanners(new MethodAnnotationsScanner())
                    .filterInputsBy((String str) -> str != null && str.endsWith(".class"))
    );
    private final static Map<String, Map<RestController.MethodType, Method>> ANNOTATION_METHOD_MAP = controllerMapInit();


    private static Map<String, Map<RestController.MethodType, Method>> controllerMapInit() {
        final ImmutableMap.Builder<String, Map<RestController.MethodType, Method>> builder = ImmutableMap.builder();
        for (RestController.MethodType methodType : RestController.MethodType.values()) {
            final Set<Method> methods = reflections.getMethodsAnnotatedWith(methodType.getAnnotation());
            for (final Method method : methods) {
                final Map<RestController.MethodType, Method> methodMap = new HashMap<>();
                methodMap.put(methodType, method);
                builder.put(ServletAnnotationUtil.getServletPath(method, methodType), methodMap);
            }
        }
        return builder.build();
    }


    public static Method findApiMethod(final String methodType, final String uri) {
        try {
            return ANNOTATION_METHOD_MAP.get(uri).get(RestController.MethodType.findMethodType(methodType));
        } catch (NullPointerException e) {
            return null;
        }
    }
}
