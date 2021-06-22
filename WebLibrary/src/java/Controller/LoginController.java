/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import BLL.UtilizadorBLL;
import DAL.Utilizador;
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
@RequestMapping(value = "/paginalogin.htm")
public class LoginController extends AbstractController {
    
    public LoginController() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        boolean isLogged = false;
        
        if(request.getParameter("submitGoToLogin") != null){
            return new ModelAndView("Login");
        }
        
        if(request.getParameter("submitLogin") != null){
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            Utilizador utilizador = new Utilizador();
            utilizador.setUsername(username);
            utilizador.setPassword(password);
            Utilizador user_txt = UtilizadorBLL.readUsername(utilizador.getUsername());
            
            try{
                if(utilizador.getUsername().equals(user_txt.getUsername()) && utilizador.getPassword().equals(user_txt.getPassword())){
                
                // Tipo utilizador 2 é Leitor.
                if(user_txt.getTipoUtilizador() == 2){
                    System.out.println("Credenciais inseridas corretamente.");
                    isLogged = true;
                    // Sessão controlar Login
                    HttpSession session = request.getSession();
                    session.setAttribute("userLogin", isLogged);
                    session.setAttribute("leitor", user_txt);
                    //return new ModelAndView("PaginaInicial", "cliente", user_txt);
                    //response.sendRedirect("paginainicial.htm");
                    ModelAndView finalModelo = new ModelAndView("PaginaInicial");
                    response.sendRedirect(request.getContextPath()+"/paginainicial.htm");
                    return finalModelo;
                } else{
                    System.out.println("Não tem permissões para entrar na Página.");
                    return new ModelAndView("Login");
                }
                
                } else{
                    System.out.println("Credenciais Erradas.");
                    return new ModelAndView("Login");
                }
            }catch(NullPointerException npe){
                npe.printStackTrace();
                return new ModelAndView("Login");
            }
        }
            return new ModelAndView("Login");
    } 
}