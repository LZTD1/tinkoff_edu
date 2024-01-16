package edu.hw10.Task2;

import edu.hw10.Task2.Annotations.Cache;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;

public class CacheProxy {

    private final ClassLoader cLoader;
    private final Class<?>[] interfaces;
    private final Object instance;
    private final HashMap<String, Object> cache = new HashMap<>();

    public CacheProxy(
        Object instance
    ) {
        this.instance = instance;
        this.cLoader = instance.getClass().getClassLoader();
        this.interfaces = instance.getClass().getInterfaces();
    }

    public Object getInstance() {
        return Proxy.newProxyInstance(
            this.cLoader,
            this.interfaces,
            (proxy, method, args) -> {
                if (method.isAnnotationPresent(Cache.class)) {
                    return getObject(method, args);
                } else {
                    return method.invoke(this.instance, args);
                }
            }
        );
    }

    private Object getObject(Method method, Object[] args)
        throws IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        if (method.getAnnotation(Cache.class).persist()) {
            return getObjectFromDisk(method, args);
        } else {
            return getObjectFromMemory(method, args);
        }

    }

    private Object getObjectFromMemory(Method method, Object[] args)
        throws InvocationTargetException, IllegalAccessException {
        var key = String.valueOf(instance.getClass().getName().hashCode())
            + (method.toString() + Arrays.toString(args)).hashCode();
        if (this.cache.containsKey(key)) {
            return this.cache.get(key);
        } else {
            Object result = method.invoke(this.instance, args);
            this.cache.put(key, result);
            return result;
        }
    }

    private Object getObjectFromDisk(Method method, Object[] args)
        throws IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        var directory = String.valueOf(instance.getClass().getName().hashCode());
        var nameMethod = String.valueOf(
            (method.getName() + Arrays.toString(args)).hashCode()
        );
        Path currentFile = Path.of(directory, nameMethod);

        if (currentFile.toFile().exists()) {
            return loadResultFromFile(currentFile);
        } else {
            return getUnwrittenResult(directory, nameMethod, method, args);
        }
    }

    private Object loadResultFromFile(Path currentFile) throws IOException, ClassNotFoundException {
        try (ObjectInputStream objStream = new ObjectInputStream(
            new FileInputStream(currentFile.toFile())
        )) {
            return objStream.readObject();
        }
    }

    private Object getUnwrittenResult(
        String directory,
        String nameMethod,
        Method method,
        Object[] args
    ) throws IOException, InvocationTargetException, IllegalAccessException {
        createFile(directory, nameMethod);
        try (ObjectOutputStream objStream = new ObjectOutputStream(
            new FileOutputStream(Path.of(directory, nameMethod).toFile())
        )) {
            Object result = method.invoke(this.instance, args);
            objStream.writeObject(result);
            return result;
        }
    }

    private void createFile(String directory, String nameMethod) throws IOException {
        var dir = Path.of(directory);
        var file = dir.resolve(nameMethod);

        if (!Files.exists(dir)) {
            Files.createDirectory(dir);
        }
        if (!Files.exists(file)) {
            Files.createFile(file);
        }
    }
}
