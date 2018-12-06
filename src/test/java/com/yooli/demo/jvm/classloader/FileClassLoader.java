package com.yooli.demo.jvm.classloader;

import java.io.*;

/**
 * Created by shaoxi.zhu
 * Date: 2018/11/12
 * Time: 上午12:42
 */
public class FileClassLoader extends ClassLoader {
    private String rootDir;

    public FileClassLoader(String rootDir) {
        this.rootDir = rootDir;
    }

    /**
     * 编写findClass方法的逻辑
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 获取类的class文件字节数组
        byte[] classData = getClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            //直接生成class对象
            return defineClass(name, classData, 0, classData.length);
        }
    }

    /**
     * 编写获取class文件并转换为字节码流的逻辑
     *
     * @param className
     * @return
     */
    private byte[] getClassData(String className) {
        // 读取类文件的字节
        String path = classNameToPath(className);
        try {
            InputStream ins = new FileInputStream(path);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 4096;
            byte[] buffer = new byte[bufferSize];
            int bytesNumRead = 0;
// 读取类文件的字节码
            while ((bytesNumRead = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesNumRead);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 类文件的完全路径
     *
     * @param className
     * @return
     */
    private String classNameToPath(String className) {
        return rootDir + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
    }

    public static void masin(String[] args) throws ClassNotFoundException {
        String rootDir = "/Users/shaoxizhu/Downloads/demo/src/test/java/";
        //创建自定义文件类加载器
        FileClassLoader loader = new FileClassLoader(rootDir);
        try {
            //加载指定的class文件
            Class<?> object1 = loader.loadClass("com.yooli.demo.jvm.classloader.DemoObj");
            System.out.println(object1.newInstance().toString());
            System.out.println(object1.getClassLoader());
            System.out.println(FileClassLoader.class.getClassLoader());
            System.out.println(object1.newInstance().getClass().getClassLoader());
            System.out.println(object1.newInstance().getClass().getClassLoader());
            //输出结果:I am DemoObj
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        String rootDir = "/Users/shaoxizhu/Downloads/demo/src/test/java/";
        //创建自定义文件类加载器
        FileClassLoader loader = new FileClassLoader(rootDir);
        FileClassLoader loader2 = new FileClassLoader(rootDir);
        try {
            //加载指定的class文件,调用loadClass()
            Class<?> object1 = loader.loadClass("com.yooli.demo.jvm.classloader.DemoObj");
            Class<?> object2 = loader2.loadClass("com.yooli.demo.jvm.classloader.DemoObj");
            System.out.println("loadClass->obj1:" + object1.hashCode());
            System.out.println("loadClass->obj2:" + object2.hashCode());
            //加载指定的class文件,直接调用findClass(),绕过检测机制，创建不同class对象。

            Class<?> object3 = loader.findClass("com.yooli.demo.jvm.classloader.DemoObj");
            Class<?> object4 = loader2.findClass("com.yooli.demo.jvm.classloader.DemoObj");
            System.out.println("loadClass->obj3:" + object3.hashCode());
            System.out.println("loadClass->obj4:" + object4.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



