/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.Entrega;
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
public class EntregaBLL {
    private static final String PERSISTENCE_UNIT_NAME = "DesktopPU";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;
    
    public static void create(Entrega entrega){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(entrega);
        em.getTransaction().commit();
    }
    
    public static void update(Entrega entrega){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.merge(entrega);
        em.getTransaction().commit();
        
    }
        
    public static Entrega read(int idEntrega){
        Entrega entrega = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Entrega.findByIdEntrega");
        q1.setParameter("identrega", idEntrega);
        Object obj = q1.getSingleResult();
        
        if(obj != null){
            entrega = ((Entrega)obj);
            //em.merge(cli);
        }
        else
            return null;
        
        
        return entrega;
    }

    public static List<Entrega> readAll(){
        List<Entrega> entregas = new ArrayList<>();
        
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Entrega.findAll");
        List<Object> lstObj = q1.getResultList();
        
        for(Object obj : lstObj){
            Entrega entrega = ((Entrega)obj);
            entregas.add(entrega);
        }        
        
        return entregas;
    }    
    
    public static void delete(Entrega entrega){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.remove(entrega);
        em.getTransaction().commit();
    }
}
