package com.iait;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.iait.enums.RegionEnum;
import com.iait.repositories.ProvinciaRepository;
import com.iait.services.ProvinciaService;

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
    private ProvinciaRepository provinciaRepository;
    
    @Autowired
    private ProvinciaService provinciaService;
    
    public void run() {
        
        provinciaRepository.findAll().forEach(provinciaEntity -> {
            System.out.println("Provincia: " +  provinciaEntity);
        });
        
        provinciaService.buscar(q -> q.region.eq(RegionEnum.PAMPEANA)).forEach(provinciaEntity -> {
            System.out.println("Provincia: " + provinciaEntity);
        });
    }
}
