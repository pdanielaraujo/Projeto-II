/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.ExemplarLivro;
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
public class ExemplarLivroBLL {   
    private static final String PERSISTENCE_UNIT_NAME = "DesktopPU";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;
    
    public static void create(ExemplarLivro exemplar){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(exemplar);
        em.getTransaction().commit();
    }
    
    public static void update(ExemplarLivro exemplar){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.merge(exemplar);
        em.getTransaction().commit();
    }
        
    public static ExemplarLivro read(int idExemplar){
        ExemplarLivro exemplar = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("ExemplarLivro.findByIdExemplar");
        q1.setParameter("idexemplar", idExemplar);
        Object obj = q1.getSingleResult();
        
        if(obj != null){
            exemplar = ((ExemplarLivro)obj);
            //em.merge(cli);
        }
        else
            return null;
        
        
        return exemplar;
    }

    public static List<ExemplarLivro> readAll(){
        List<ExemplarLivro> exemplares = new ArrayList<>();
        
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("ExemplarLivro.findAll");
        List<Object> lstObj = q1.getResultList();
        
        for(Object obj : lstObj){
            ExemplarLivro exemplar = ((ExemplarLivro)obj);
            exemplares.add(exemplar);
        }        
        
        return exemplares;
    }
}
