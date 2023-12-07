package edu.hw10.Task1.Transformations;

import java.lang.annotation.Annotation;

public interface Transformation {
    Object get(Annotation[] annotations);

    Object annotationsFabric(Annotation[] annotations);
}
