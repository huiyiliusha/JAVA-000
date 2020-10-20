package com.hello;


import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassLoader extends ClassLoader{

    public static void main(String[] args) {
        try {
            MyClassLoader myClassLoader = new MyClassLoader();
            Class<?> hello = myClassLoader.findClass("Hello");
            Object a = hello.newInstance();//加载hello类，创建实例并初始化
            Method method = hello.getMethod("hello");
            method.invoke(a);

        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try{
            byte[] helloBytes = convertHelloBytes(name);
            return defineClass(name, helloBytes, 0, helloBytes.length);
        }catch (Exception e){
            throw new ClassNotFoundException(e.getMessage());
        }

    }
    private byte[] convertHelloBytes(String name){
        String path = this.getClass().getResource("/").getPath() + name + ".xlass";
        File file = new File(path);
        byte[] data = null;
        try(FileInputStream in = new FileInputStream(file)){
            data = new byte[in.available()];
            int byteNum = -1;
            in.read(data);
            for(int offset = 0; offset < data.length; offset++){
                data[offset] = (byte) (255- data[offset]);
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return data;
        }
    }
}
