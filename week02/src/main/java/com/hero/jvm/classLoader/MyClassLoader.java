package com.hero.jvm.classLoader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyClassLoader extends ClassLoader{

    private String classPath;

    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte [] classData = getData(name);
        return defineClass(name, classData, 0, classData.length);
    }

    private byte[] getData (String className) {
        String path = classPath + File.separator + className.replace(".", File.separator) + ".class";
        try (InputStream in = Files.newInputStream(Paths.get(path)); ByteArrayOutputStream out = new ByteArrayOutputStream();){
            byte [] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            return out.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
