package com.jerry.demo.usercenter.services;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/application-context.xml");
        context.registerShutdownHook();

        System.out.println("Profile service startup finished.");

        while (true) {
            try {
                Thread.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
