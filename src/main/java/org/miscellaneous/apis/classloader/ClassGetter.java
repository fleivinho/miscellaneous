package org.miscellaneous.apis.classloader;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassGetter {

    public static List<Class<?>> getClassesForPackageByFile(File file, String packageName) {
        List<Class<?>> classes = new ArrayList<>();
        try (JarFile jarFile = new JarFile(file)) {
            String relPath = packageName.replace('.', '/');
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String entryName = entry.getName();
                if (entryName.endsWith(".class")
                        && entryName.startsWith(relPath)
                        && entryName.length() > (relPath.length() + 1)) {
                    String className = entryName.replace('/', '.').replace('\\', '.').replace(".class", "");
                    classes.add(loadClass(className));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load classes from JAR file '" + file.getAbsolutePath() + "'", e);
        }
        return classes;
    }

    public static List<Class<?>> getClassesForPackage(Object instance, String packageName) {
        CodeSource source = instance.getClass().getProtectionDomain().getCodeSource();
        List<Class<?>> classes = new ArrayList<>();
        if (source != null) {
            processJarFile(source.getLocation(), packageName, classes);
        }
        classes.sort((c1, c2) -> c1.getSimpleName().compareToIgnoreCase(c2.getSimpleName()));
        return classes;
    }

    private static Class<?> loadClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load class '" + className + "'", e);
        }
    }

    private static void processJarFile(URL resource, String packageName, List<Class<?>> classes) {
        String relPath = packageName.replace('.', '/');
        String resPath = resource.getPath().replace("%20", " ");
        String jarPath = resPath.replaceFirst("[.]jar[!].*", ".jar").replaceFirst("file:", "");
        try (JarFile jarFile = new JarFile(jarPath)) {
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String entryName = entry.getName();
                if (entryName.endsWith(".class") && entryName.startsWith(relPath) && entryName.length() > (relPath.length() + 1)) {
                    String className = entryName.replace('/', '.').replace('\\', '.').replace(".class", "");
                    classes.add(loadClass(className));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JAR file '" + jarPath + "'", e);
        }
    }
}
