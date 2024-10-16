package com.cycas.netty.util;

import com.cycas.netty.protocol.Packet;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author xin.na
 * @since 2024/10/14 17:07
 */
public class ReflectionUtil {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Class<Packet> abstractClass = Packet.class;
        List<Class<?>> implementations  = getAllImplementations(abstractClass);
        implementations.forEach(System.out::println);
    }

    public static List<Class<?>> getAllImplementations(Class<?> abstractClass) throws IOException, ClassNotFoundException {
        List<Class<?>> implementations = new ArrayList<>();

        String packageName = abstractClass.getPackage().getName();
        List<Class<?>> allClasses = getClasses(packageName);

        for (Class<?> clazz :allClasses){
            // 排除抽象类或接口本身，确保是非抽象类的具体实现类
            if (!clazz.isInterface() && !Modifier.isAbstract(clazz.getModifiers()) && abstractClass.isAssignableFrom(clazz)) {
                implementations.add(clazz);
            }
        }
        return implementations;
    }

    public static List<Class<?>> getClasses(String packageName) throws IOException, ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace(".", "/");
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        List<Class<?>> classes = new ArrayList<Class<?>>();
        for (File dir : dirs) {
            classes.addAll(findClasses(dir, packageName));
        }
        return classes;
    }

    private static List<Class<?>> findClasses(File dir, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (!dir.exists()) {
            return classes;
        }
        File[] files = dir.listFiles();
        if (files == null) {
            return classes;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + "." + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
