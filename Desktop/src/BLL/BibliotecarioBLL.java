/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.Bibliotecario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Pedro
 */
public class BibliotecarioBLL {
    private static final String PERSISTENCE_UNIT_NAME = "DesktopPU";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;
    
    public static void create(Bibliotecario bibliotecario){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(bibliotecario);
        em.getTransaction().commit();
    }
    
    public static void update(Bibliotecario bibliotecario){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.merge(bibliotecario);
        em.getTransaction().commit();
        
    }
        
    public static Bibliotecario read(int idBibliotecario){
        Bibliotecario bibliotecario = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Bibliotecario.findByIdBibliotecario");
        q1.setParameter("idbibliotecario", idBibliotecario);
        Object obj = q1.getSingleResult();
        
        if(obj != null){
            bibliotecario = ((Bibliotecario)obj);
            //em.merge(cli);
        }
        else
            return null;
        
        
        return bibliotecario;
    }

    public static List<Bibliotecario> readAll(){
        List<Bibliotecario> bibliotecarios = new ArrayList<>();
        
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Bibliotecario.findAll");
        List<Object> lstObj = q1.getResultList();
        
        for(Object obj : lstObj){
            Bibliotecario bibliotecario = ((Bibliotecario)obj);
            bibliotecarios.add(bibliotecario);
        }        
        
        return bibliotecarios;
    }    
    
    public static void delete(Bibliotecario bibliotecario){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.remove(bibliotecario);
        em.getTransaction().commit();
    }
}
