package com.iait;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.iait.repositories.ProvinciaRepository;

public class App {
    
    public static final ApplicationContext CONTEXT;
    
    static {
        CONTEXT = new ClassPathXmlApplicationContext("app-config.xml");
    }
    
    public static void main(String[] args) {
        App app = CONTEXT.getBean(App.class);
        app.run();
    }
    
    @Autowired
    ProvinciaRepository provinciaRepository;
    
    public void run() {
        
        provinciaRepository.findAll().forEach(provinciaEntity -> {
            System.out.println("Provincia: " +  provinciaEntity);
        });
    }
}
