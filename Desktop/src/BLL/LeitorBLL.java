/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.Leitor;
import DAL.Lingua;
import java.math.BigDecimal;
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
public class LeitorBLL {
    private static final String PERSISTENCE_UNIT_NAME = "DesktopPU";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;
    
    public static List<Leitor> readAll(){
        List<Leitor> leitor = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        if (em == null)
            em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Leitor.findAll");
        List<Object> result = q1.getResultList();
        
        for(Object obj : result){
            //Lingua lng = ((Lingua)obj);
            leitor.add((Leitor)obj);
        }        
        
        return leitor;
    }
    
    public static Leitor read(BigDecimal idUtilizador){
        Leitor leitor = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Leitor.findByUtilizador");
        q1.setParameter("idUtilizador", idUtilizador);
        Object obj = q1.getSingleResult();
        
        if(obj != null){
            leitor = ((Leitor)obj);
            //em.merge(cli);
        }
        else
            return null;
        
        
        return leitor;
    }
}
