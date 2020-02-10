package com.company.fouthtask;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        MyClassLoader loader = new MyClassLoader();
        Class clazz = loader.findClass("LogOutStrategy");
        PrintStrategy logOutStrategy = (PrintStrategy) clazz.newInstance();
        Service service = new Service();
        service.process(new SystemOutStrategy());
        service.process(logOutStrategy);
    }
}
