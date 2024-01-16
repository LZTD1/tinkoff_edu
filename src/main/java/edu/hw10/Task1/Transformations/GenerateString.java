package edu.hw10.Task1.Transformations;

import edu.hw10.Task1.Anntotaions.NotNull;
import edu.hw10.Task1.Exceptions.NoFoundValueError;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

public class GenerateString implements Transformation {

    private final static Map<Class<?>, Object> ANNOTATIONS = new HashMap<>() {{
        put(NotNull.class, "");
    }};

    @Override
    public Object get(Annotation[] annotations) {
        if (annotations.length > 0) {
            return annotationsFabric(annotations);
        }
        return null;
    }

    public Object annotationsFabric(Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            if (ANNOTATIONS.containsKey(annotation.annotationType())) {
                return ANNOTATIONS.get(annotation.annotationType());
            } else {
                throw new NoFoundValueError(annotation.toString());
            }
        }
        return null;
    }

}
