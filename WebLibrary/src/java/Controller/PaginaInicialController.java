/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import BLL.EstadoBLL;
import BLL.ExemplarLivroBLL;
import BLL.LeitorBLL;
import BLL.RequisicaoBLL;
import DAL.Estado;
import DAL.ExemplarLivro;
import DAL.Leitor;
import DAL.Requisicao;
import DAL.Utilizador;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
                System.out.println(estados_.getNome());
            }
            
            // Buscar o valor do estado «Requisitado»
            Estado estadoRequisitado = lista_estados.get(1);
            Estado estadoNaoRequisitado = lista_estados.get(2);
            
            // ID do exemplar vem do JSP
            String idExemplar = request.getParameter("idExemplar");
            
            // Transformar o ID em BigDecimal
            BigDecimal exemplarIdBigDecimal = new BigDecimal(idExemplar);
            
            // Buscar o exemplar com esse ID
            ExemplarLivro exemplar = ExemplarLivroBLL.read(exemplarIdBigDecimal);
            Estado estadoJSP = exemplar.getEstadoId();
            
            // 3 é o estado «Não Requisitado»
            if(exemplar.getEstadoId().equals(estadoNaoRequisitado)){
                // Se o estado deste exemplar for «Não Requisitado», atualiza o seu estado para «Requisitado»
                ExemplarLivroBLL.updateEstado(exemplar.getIdExemplar(), estadoRequisitado);
              
                // Obter a data atual
                Date dataAtual = new Date();
                DateFormat outputFormatDataAtual = new SimpleDateFormat("dd-MM-yyyy");
                String dataReqFinalString = outputFormatDataAtual.format(dataAtual);
                Date dataReqFinal = outputFormatDataAtual.parse(dataReqFinalString);
                
                // Buscar o tempo de requisição
                String tempoRequisicaoString = request.getParameter("tempoRequisicao");
                long tempoRequisicao = Long.valueOf(tempoRequisicaoString);
                
                // ID do user que vem do JSP
                HttpSession session = request.getSession();
                Utilizador leitorUser = (Utilizador) session.getAttribute("leitor");
                
                // Buscar o Leitor com este user ID
                Leitor leitorReserva = LeitorBLL.read(leitorUser.getIdUtilizador());
                
                
                // Obter a data prevista de entrega, sumando o tempo de requisição à data atual
                LocalDate date = convertToLocalDateViaInstant(dataReqFinal);
                LocalDate novaData = date.plusDays(tempoRequisicao);
                Date dataPrevEntrega = convertToDateViaSqlDate(novaData);
                String dataPrevEntregaString = outputFormatDataAtual.format(dataPrevEntrega);
                Date dataPrevEntregaFinal = outputFormatDataAtual.parse(dataPrevEntregaString);
                
                // Criar requisição
                Requisicao requisicao = new Requisicao();
                requisicao.setLeitorId(leitorReserva);
                requisicao.setDataReq(dataReqFinal);
                requisicao.setTempReq(tempoRequisicao);
                requisicao.setDataPrevEntrega(dataPrevEntregaFinal);
                requisicao.setExemplarId(exemplar);
                RequisicaoBLL.create(requisicao);
                System.out.println("Este livro foi atualizado.");
                System.out.println("Requisição criada com sucesso.");
            } else if(exemplar.getEstadoId().equals(estadoRequisitado)){
                System.out.println("Este livro já se encontra requisitado.");
            }
            response.sendRedirect(request.getContextPath()+"/paginainicial.htm");
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
    
    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate();
    }
    
    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }
}
