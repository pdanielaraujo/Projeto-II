/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.Entrega;
import DAL.Requisicao;
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
public class RequisicaoBLL {
    private static final String PERSISTENCE_UNIT_NAME = "DesktopPU";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;
    
    public static void create(Requisicao requisicao){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(requisicao);
        em.getTransaction().commit();
    }
    
    public static void update(Requisicao requisicao){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.merge(requisicao);
        em.getTransaction().commit();
    }
    
    public static void updateEntrega(BigDecimal idRequisicao, Entrega entregaId){
        
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        if (em == null) em = factory.createEntityManager();
        
        
        Query q1 = em.createNamedQuery("Requisicao.updateEntrega");
        q1.setParameter("idRequisicao", idRequisicao);
        q1.setParameter("entregaId", entregaId);
        em.getTransaction().begin();
        q1.executeUpdate();
        //em.merge(exemplar);
        em.getTransaction().commit();
        
        //return exemplar;
    }
        
    public static Requisicao read(int idRequisicao){
        Requisicao requisicao = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Requisicao.findByIdRequisicao");
        q1.setParameter("idrequisicao", idRequisicao);
        Object obj = q1.getSingleResult();
        
        if(obj != null){
            requisicao = ((Requisicao)obj);
            //em.merge(cli);
        }
        else
            return null;
        
        
        return requisicao;
    }

    public static List<Requisicao> readAll(){
        List<Requisicao> requisicoes = new ArrayList<>();
        
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        if (em == null) em = factory.createEntityManager();
        
        Query q1 = em.createNamedQuery("Requisicao.findAll");
        List<Object> lstObj = q1.getResultList();
        
        for(Object obj : lstObj){
            Requisicao requisicao = ((Requisicao)obj);
            requisicoes.add(requisicao);
        }        
        
        return requisicoes;
    }    
    
    public static void delete(Requisicao requisicao){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
        if (em == null) em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.remove(requisicao);
        em.getTransaction().commit();
    }
}
