package com.iait;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
    
    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
    
    public void run() {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("calculadora-prestamo");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        int size = em.createNativeQuery("select * from localidades;").getResultList().size();
        System.out.println(size);
        
        em.getTransaction().commit();
        em.close();
    }
}
