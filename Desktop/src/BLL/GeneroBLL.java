/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.Genero;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Pedro
 */
public class GeneroBLL {
    private static final String PERSISTENCE_UNIT_NAME = "DesktopPU";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;
    
    public static void create(Genero genero){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(genero);
        em.getTransaction().commit();
    }
    
    public static void update(Genero genero){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.merge(genero);
        em.getTransaction().commit();
        
    }
        
    public static Genero read(int idGenero){
        Genero genero = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Genero.findByIdGenero");
        q1.setParameter("idgenero", idGenero);
        Object obj = q1.getSingleResult();
        
        if(obj != null){
            genero = ((Genero)obj);
            //em.merge(cli);
        }
        else
            return null;
        
        
        return genero;
    }

    public static List<Genero> readAll(){
        List<Genero> generos = new ArrayList<>();
        
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Genero.findAll");
        List<Object> lstObj = q1.getResultList();
        
        for(Object obj : lstObj){
            Genero genero = ((Genero)obj);
            generos.add(genero);
        }        
        
        return generos;
    }
    
    public static List<Genero> readAllNomeGenero(){
        List<Genero> generos = new ArrayList<>();
        
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Genero.findNomeGenero");
        List<Object> lstObj = q1.getResultList();
        
        for(Object obj : lstObj){
            //Genero genero = ((Genero)obj);
            Genero genero = new Genero();
            genero.setNome((String) obj);
            System.out.println(obj);
            generos.add(genero);
        }        
        
        return generos;
    }
    
    public static void delete(Genero genero){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.remove(genero);
        em.getTransaction().commit();
    }
}
