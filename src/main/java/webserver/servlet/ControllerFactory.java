package webserver.servlet;

import com.google.common.collect.ImmutableMap;
import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.controller.ServletController;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

public class ControllerFactory {
    private static final Logger log = LoggerFactory.getLogger(ControllerFactory.class);
    private static final Map<String, ServletController> CONTROLLER_MAP = init();

    private static Map<String, ServletController> init() {
        final ConfigurationBuilder configurationBuilder = new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage("webserver"))
                .setScanners(new TypeAnnotationsScanner())
                .filterInputsBy((String str) -> str != null && str.endsWith(".class"));
        final Reflections reflections = new Reflections(configurationBuilder);
        final Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Controller.class, true);
        final ImmutableMap.Builder<String, ServletController> builder = new ImmutableMap.Builder<>();

        classes.forEach(aClass -> {
            try {
                final Controller controller = aClass.getAnnotation(Controller.class);
                log.info("{}, {}", aClass.getName(), controller.path());
                builder.put(controller.path(), (ServletController) aClass.newInstance());
            } catch (final Exception e) {
                log.error("======= class loader error ========", e);
            }
        });
        return builder.build();
    }

    private static ServletController getController(final String uri) {
        return CONTROLLER_MAP.get(uri);
    }

    /**
     * 작업중임.
     * @param uri
     * @param method
     * @return
     */
    private static Method getMethod(final String uri, final String method) {
        Annotation annotation = CONTROLLER_MAP.get(uri).getClass().getAnnotation(Controller.MethodType.findMethodType(method).getAnnotation());
        return null;
    }
}
