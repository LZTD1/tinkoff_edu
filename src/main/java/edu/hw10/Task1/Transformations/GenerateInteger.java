package edu.hw10.Task1.Transformations;

import edu.hw10.Task1.Anntotaions.Max;
import edu.hw10.Task1.Anntotaions.Min;
import edu.hw10.Task1.Exceptions.NoFoundValueError;
import java.lang.annotation.Annotation;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateInteger implements Transformation {

    @Override
    public Object get(Annotation[] annotations) {

        return annotationsFabric(annotations);
    }

    @Override
    public Object annotationsFabric(Annotation[] annotations) {
        var max = Integer.MAX_VALUE;
        var min = Integer.MIN_VALUE;

        if (annotations.length > 0) {
            for (var annotation : annotations) {
                if (annotation instanceof Max) {
                    max = ((Max) annotation).value();
                } else if (annotation instanceof Min) {
                    min = ((Min) annotation).value();
                } else {
                    throw new NoFoundValueError(annotation.toString());
                }
            }

            return ThreadLocalRandom.current().nextInt(min, max);
        } else {
            return 0;
        }
    }
}
