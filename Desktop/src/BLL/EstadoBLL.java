/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.Estado;
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
public class EstadoBLL {
    private static final String PERSISTENCE_UNIT_NAME = "DesktopPU";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;
    
    public static void update(Estado estado){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.merge(estado);
        em.getTransaction().commit();
    }
        
    public static Estado read(int idEstado){
        Estado estado = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Estado.findByIdEstado");
        q1.setParameter("idestado", idEstado);
        Object obj = q1.getSingleResult();
        
        if(obj != null){
            estado = ((Estado)obj);
            //em.merge(cli);
        }
        else
            return null;
        
        
        return estado;
    }

    public static List<Estado> readAll(){
        List<Estado> estados = new ArrayList<>();
        
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Estado.findAll");
        List<Object> lstObj = q1.getResultList();
        
        for(Object obj : lstObj){
            Estado estado = ((Estado)obj);
            estados.add(estado);
        }        
        
        return estados;
    }
}
