/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.Lingua;
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
public class LinguaBLL {
    private static final String PERSISTENCE_UNIT_NAME = "DesktopPU";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;
    
    public static List<Lingua> readAll(){
        List<Lingua> lingua = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        if (em == null)
            em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Lingua.findAll");
        List<Object> result = q1.getResultList();
        
        for(Object obj : result){
            //Lingua lng = ((Lingua)obj);
            lingua.add((Lingua)obj);
        }        
        
        return lingua;
    }
}
