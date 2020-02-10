package com.company.fouthtask;

import java.io.*;

public class MyClassLoader extends ClassLoader {

    public Class<?> findClass(String className) {
        try {
            File file = new File(this.getClass().getClassLoader().getResource(className + ".class").getFile());
            try (InputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
                byte[] bytes = new byte[(int)file.length()];
                inputStream.read(bytes);
                return defineClass(null, bytes, 0, bytes.length);
            }
        } catch (IOException e) {
            throw new MyClassLoaderException(e.getMessage(), e);
        }
    }
}
