/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.Utilizador;
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
public class UtilizadorBLL {
    private static final String PERSISTENCE_UNIT_NAME = "DesktopPU";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;
    
    public static void create(Utilizador utilizador){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(utilizador);
        em.getTransaction().commit();
    }
    
    public static void update(Utilizador utilizador){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.merge(utilizador);
        em.getTransaction().commit();
        
    }
        
    public static Utilizador read(int idUtilizador){
        Utilizador utilizador = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Utilizador.findByIdUtilizador");
        q1.setParameter("idutilizador", idUtilizador);
        Object obj = q1.getSingleResult();
        
        if(obj != null){
            utilizador = ((Utilizador)obj);
            //em.merge(cli);
        }
        else
            return null;
        
        
        return utilizador;
    }

    public static List<Utilizador> readAll(){
        List<Utilizador> utilizadores = new ArrayList<>();
        
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Utilizador.findAll");
        List<Object> lstObj = q1.getResultList();
        
        for(Object obj : lstObj){
            Utilizador utilizador = ((Utilizador)obj);
            utilizadores.add(utilizador);
        }        
        
        return utilizadores;
    }    
    
    public static void delete(Utilizador utilizador){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.remove(utilizador);
        em.getTransaction().commit();
    }
}
