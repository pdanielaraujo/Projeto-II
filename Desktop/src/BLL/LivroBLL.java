/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.Livro;
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
public class LivroBLL {
    private static final String PERSISTENCE_UNIT_NAME = "DesktopPU";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;
    
    public static void create(Livro livro){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(livro);
        em.getTransaction().commit();
    }
    
    public static void update(Livro livro){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.merge(livro);
        em.getTransaction().commit();
        
    }
        
    public static Livro read(int idLivro){
        Livro livro = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Livro.findByIdLivro");
        q1.setParameter("idlivro", idLivro);
        Object obj = q1.getSingleResult();
        
        if(obj != null){
            livro = ((Livro)obj);
            //em.merge(cli);
        }
        else
            return null;
        
        
        return livro;
    }

    public static List<Livro> readAll(){
        List<Livro> livros = new ArrayList<>();
        
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Livro.findAll");
        List<Object> lstObj = q1.getResultList();
        
        for(Object obj : lstObj){
            Livro livro = ((Livro)obj);
            livros.add(livro);
        }        
        
        return livros;
    }    
    
    public static void delete(Livro livro){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.remove(livro);
        em.getTransaction().commit();
    }
}
