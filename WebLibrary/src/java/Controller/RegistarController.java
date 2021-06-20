/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import BLL.LeitorBLL;
import BLL.UtilizadorBLL;
import DAL.Leitor;
import DAL.Utilizador;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author Pedro
 */
@Controller
@RequestMapping(value = "/paginaregistar.htm")
public class RegistarController extends AbstractController {
    
    public RegistarController() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        if(request.getParameter("submitGoToRegister") != null){
            return new ModelAndView("Registar");
        }
        
        // vem do index, recolhe os dados dos text fields e cria user
        if(request.getParameter("submitRegister") != null){
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String nome = request.getParameter("nome");
            String morada = request.getParameter("morada");
            String dataNascimentoInicial = request.getParameter("dataNascimento");
                        
            SimpleDateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd");
            Date data_parsed = (Date) inputFormatter.parse(dataNascimentoInicial);
            
            SimpleDateFormat outputFormatter = new SimpleDateFormat("dd-MM-yyyy");
            String dataNascimentoFinalString = outputFormatter.format(data_parsed);
            Date dataNascimentoFinal = outputFormatter.parse(dataNascimentoFinalString);
            
            System.out.println(dataNascimentoFinal);
            
            
            Utilizador utilizador = new Utilizador();
            utilizador.setUsername(username);
            utilizador.setEmail(email);
            utilizador.setPassword(password);
            utilizador.setTipoUtilizador(2);
            UtilizadorBLL.create(utilizador);
            Leitor leitor = new Leitor();
            leitor.setNome(nome);
            leitor.setMorada(morada);
            leitor.setDataNascimento(dataNascimentoFinal);
            leitor.setUtilizadorId(utilizador);
            LeitorBLL.create(leitor);
            
            System.out.println("Utilizador e Leitor criados com sucesso.");
            
            response.sendRedirect(request.getContextPath()+"/paginalogin.htm");
            return new ModelAndView("Login");
        }
        //throw new UnsupportedOperationException("Not yet implemented");
        return new ModelAndView("index");
    }
    
}
