package webserver.servlet;

import org.jetbrains.annotations.Nullable;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.request.http.HttpMethod;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ReflectionsFactory {
    private final static Logger log = LoggerFactory.getLogger(ReflectionsFactory.class);
    private final static Reflections reflections = new Reflections(
            new ConfigurationBuilder()
                    .setUrls(ClasspathHelper.forPackage(Config.WEB_SERVER_PACKAGE_NAME.value()))
                    .setScanners(new MethodAnnotationsScanner())
                    .filterInputsBy((String str) -> str != null && str.endsWith(".class"))
    );
    private final static Map<String, Map<RestController.MethodType, Method>> ANNOTATION_METHOD_MAP = controllerMapInit();

    private static Map<String, Map<RestController.MethodType, Method>> controllerMapInit() {
        final HashMap<String, Map<RestController.MethodType, Method>> builderMap = new HashMap<>();
        for (RestController.MethodType methodType : RestController.MethodType.values()) {
            reflections.getMethodsAnnotatedWith(methodType.getAnnotation()).forEach(
                    method -> builderMap.computeIfAbsent(ServletAnnotationUtil.getServletPath(method, methodType), (key) -> new HashMap<>()).put(methodType, method)
            );
        }
        return builderMap;
    }

    @Nullable
    public static Method findApiMethod(final HttpMethod methodType, final String uri) {
        return Optional.ofNullable(ANNOTATION_METHOD_MAP.get(uri))
                .orElseGet(Collections::emptyMap)
                .get(RestController.MethodType.findMethodType(methodType));
    }
}
