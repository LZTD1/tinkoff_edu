package edu.hw10.Task1;

import edu.hw10.Task1.Exceptions.MethodIsNofAFabricError;
import edu.hw10.Task1.Exceptions.MethodIsNotFindedError;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import static edu.hw10.Task1.ParamsGenerator.getValidParameters;

public class RandomObjectGenerator {

    public RandomObjectGenerator() {
    }

    public Object nextObject(Class toInstance) {
        Constructor[] constructor = toInstance.getDeclaredConstructors();
        var instance = getInstance(constructor[0]);
        return instance;
    }

    public Object nextObject(Class toInstance, String fabricName) {
        Method fabric = getFabricMethod(toInstance, fabricName);
        var instance = getInstance(fabric, toInstance);

        return instance;
    }

    private Object getInstance(Method fabric, Class toInstance) {
        Parameter[] parameters = fabric.getParameters();
        Object instance;
        if (parameters.length == 0) {
            instance = getInstanceWithoutParametersByFabric(toInstance, fabric);
        } else {
            instance = getInstanceWithParametersByFabric(toInstance, fabric, parameters);
        }

        if (toInstance.isInstance(instance)) {
            return instance;
        } else {
            throw new MethodIsNofAFabricError();
        }
    }

    private Object getInstanceWithParametersByFabric(Class toInstance, Method method, Parameter[] parameters) {
        Object[] genParams = getValidParameters(parameters);

        try {
            return method.invoke(toInstance, genParams);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private Object getInstanceWithoutParametersByFabric(Class toInstance, Method fabric) {
        try {
            return fabric.invoke(toInstance);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private Method getFabricMethod(Class toInstance, String fabricName) {
        Method[] methods = toInstance.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(fabricName)) {
                return method;
            }
        }
        throw new MethodIsNotFindedError();
    }

    private Object getInstance(Constructor constructor) {
        Parameter[] parameters = constructor.getParameters();
        if (parameters.length == 0) {
            return getInstanceWithoutParameters(constructor);
        } else {
            return getInstanceWithParameters(constructor, parameters);
        }
    }

    private Object getInstanceWithParameters(Constructor constructor, Parameter[] parameters) {
        Object[] genParams = getValidParameters(parameters);

        try {
            return constructor.newInstance(genParams);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private Object getInstanceWithoutParameters(Constructor constructor) {
        try {
            return constructor.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

}
