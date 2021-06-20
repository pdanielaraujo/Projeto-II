/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import BLL.EstadoBLL;
import BLL.ExemplarLivroBLL;
import DAL.Estado;
import DAL.ExemplarLivro;
import DAL.Utilizador;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author Pedro
 */
@Controller
@RequestMapping(value = "/paginainicial.htm")
public class PaginaInicialController extends AbstractController {
    
    public PaginaInicialController() {
    }
    ModelAndView finalModelo = new ModelAndView("PaginaInicial");
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
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
            
            // 3 é o estado «Não Requisitado»
            if(exemplar.getEstadoId().equals(estadoNaoRequisitado)){
                // Se o estado deste exemplar for «Não Requisitado», atualiza o seu estado para «Requisitado»
                ExemplarLivroBLL.updateEstado(exemplar.getIdExemplar(), estadoRequisitado);
                System.out.println("Este livro foi atualizado.");
            } else if(exemplar.getEstadoId().equals(estadoRequisitado)){
                System.out.println("Este livro já se encontra requisitado.");
            }
            
            ArrayList<ExemplarLivro> lista_exemplares = new ArrayList<>();
            List<ExemplarLivro> exemplares = ExemplarLivroBLL.readAll();
        
            for(ExemplarLivro exemplares_ : exemplares){
                //System.out.println("Livro: " + exemplares_.getAutorList());
                lista_exemplares.add(new ExemplarLivro(exemplares_.getIdExemplar(), exemplares_.getLivroId(), exemplares_.getNumPaginas(), exemplares_.getLinguaId(), exemplares_.getEdicaoId(), exemplares_.getEstadoId()));
                lista_exemplares.toString();
            }
            
            finalModelo.addObject("lista", lista_exemplares);
            return finalModelo;
        }
        
        //boolean isLogged = Boolean.parseBoolean(request.getParameter("loginStatus"));
        try{
            HttpSession session = request.getSession();
            boolean isLogged = (boolean) session.getAttribute("userLogin");
            
            if(isLogged){
                ArrayList<ExemplarLivro> lista_exemplares = new ArrayList<>();
                List<ExemplarLivro> exemplares = ExemplarLivroBLL.readAll();
        
                for(ExemplarLivro exemplares_ : exemplares){
                    //System.out.println("Livro: " + exemplares_.getAutorList());
                    lista_exemplares.add(new ExemplarLivro(exemplares_.getIdExemplar(), exemplares_.getLivroId(), exemplares_.getNumPaginas(), exemplares_.getLinguaId(), exemplares_.getEdicaoId(), exemplares_.getEstadoId()));
                    lista_exemplares.toString();
                }
            
                finalModelo.addObject("lista", lista_exemplares);
                return finalModelo;
            } else {
                ModelAndView modelIndex = new ModelAndView("index");
                response.sendRedirect(request.getContextPath()+"/index.htm");
                return modelIndex;
            }
        }catch(NullPointerException npe){
            ModelAndView modelIndex = new ModelAndView("index");
            response.sendRedirect(request.getContextPath()+"/index.htm");
            return modelIndex;
        }   
    }
}
