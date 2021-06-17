/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import BLL.EstadoBLL;
import BLL.ExemplarLivroBLL;
import BLL.GeneroBLL;
import BLL.LivroBLL;
import BLL.UtilizadorBLL;
import DAL.Estado;
import DAL.ExemplarLivro;
import DAL.Genero;
import DAL.Livro;
import DAL.Utilizador;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author Pedro
 */
public class PaginaInicialController extends AbstractController {
    
    public PaginaInicialController() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        if(request.getParameter("submitver") != null){
            ArrayList<ExemplarLivro> lista_exemplares = new ArrayList<>();
            List<ExemplarLivro> exemplares = ExemplarLivroBLL.readAll();
        
            for(ExemplarLivro exemplares_ : exemplares){
                //System.out.println("Livro: " + exemplares_.getAutorList());
                lista_exemplares.add(new ExemplarLivro(exemplares_.getIdExemplar(), exemplares_.getLivroId(), exemplares_.getNumPaginas(), exemplares_.getLinguaId(), exemplares_.getEdicaoId(), exemplares_.getEstadoId()));
                lista_exemplares.toString();
            }
            
            ModelAndView finalModelo = new ModelAndView("PaginaInicial");
            finalModelo.addObject("lista", lista_exemplares);
            return finalModelo;
        }
        
        if(request.getParameter("submitReservar") != null){
            
            
            List<Estado> lista_estados = new ArrayList<>();
            List<Estado> estados = EstadoBLL.readAll();
            
            // Ir buscar todos os estados
            for(Estado estados_ : estados){
                //String nomeGenero = generos_.getNome();
                lista_estados.add(new Estado(estados_.getIdEstado(), estados_.getNome()));
                lista_estados.toString();
            }
            
            System.out.println("Estado: " + lista_estados.get(1));
            // Buscar o valor do estado «Requisitado»
            Estado estadoRequisitado = lista_estados.get(1);
            Estado estadoNaoRequisitado = lista_estados.get(2);
            
            // ID do exemplar vem do JSP
            String idExemplar = request.getParameter("idExemplar");
            System.out.println(idExemplar);
            
            // Transformar o ID em BigDecimal
            BigDecimal exemplarIdBigDecimal = new BigDecimal(idExemplar);
            
            // Buscar o exemplar com esse ID
            ExemplarLivro exemplar = ExemplarLivroBLL.read(exemplarIdBigDecimal);
            Estado estadoJSP = exemplar.getEstadoId();
            System.out.println("aaa" + estadoJSP);
            System.out.println("bbb" + estadoNaoRequisitado);
            
            // 3 é o estado «Não Requisitado»
            if(exemplar.getEstadoId().equals(estadoNaoRequisitado)){
                // Se o estado deste exemplar for «Não Requisitado», atualiza o seu estado para «Requisitado»
                ExemplarLivroBLL.updateEstado(exemplar.getIdExemplar(), estadoRequisitado);
                System.out.println("Este livro foi atualizado.");
            } else if(exemplar.getEstadoId().equals(estadoRequisitado)){
                System.out.println("Este livro já se encontra requisitado.");
            }
            
            //BigDecimal estadoBigDecimal = new BigDecimal(estadoId);
            
            System.out.println("exemplar" + exemplarIdBigDecimal);
            
            ArrayList<ExemplarLivro> lista_exemplares = new ArrayList<>();
            List<ExemplarLivro> exemplares = ExemplarLivroBLL.readAll();
        
            for(ExemplarLivro exemplares_ : exemplares){
                //System.out.println("Livro: " + exemplares_.getAutorList());
                lista_exemplares.add(new ExemplarLivro(exemplares_.getIdExemplar(), exemplares_.getLivroId(), exemplares_.getNumPaginas(), exemplares_.getLinguaId(), exemplares_.getEdicaoId(), exemplares_.getEstadoId()));
                lista_exemplares.toString();
            }
            
            ModelAndView finalModelo = new ModelAndView("PaginaInicial");
            finalModelo.addObject("lista", lista_exemplares);
            return finalModelo;
        }
        
        
        
        
        return new ModelAndView("index");
    }
    
}
