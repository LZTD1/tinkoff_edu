package edu.hw10.Task1;

import edu.hw10.Task1.Exceptions.NoInformationForParametersError;
import edu.hw10.Task1.Transformations.GenerateInteger;
import edu.hw10.Task1.Transformations.GenerateString;
import edu.hw10.Task1.Transformations.Transformation;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

public class ParamsGenerator {

    private final static Map<Class<?>, Transformation> TYPES = new HashMap<>() {{
        put(Integer.class, new GenerateInteger());
        put(int.class, new GenerateInteger());
        put(String.class, new GenerateString());
    }};

    private ParamsGenerator() {
    }

    public static Object[] getValidParameters(Parameter[] parameters) {
        Object[] parametersValues = new Object[parameters.length];

        for (int i = 0; i < parameters.length; i++) {
            if (TYPES.containsKey(parameters[i].getType())) {
                parametersValues[i] = TYPES.get(parameters[i].getType()).get();
            } else {
                throw new NoInformationForParametersError(parameters[i].getType().getName());
            }
        }

        return parametersValues;
    }

}
