package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        var contactManagementSystem = context.getBean(ContactManagementSystem.class);
        contactManagementSystem.run();
    }
}
