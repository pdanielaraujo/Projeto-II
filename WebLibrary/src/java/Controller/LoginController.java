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
    
    ModelAndView finalLogin = new ModelAndView("Login");
    ModelAndView finalRegister = new ModelAndView("Registar");
    ModelAndView finalIndex = new ModelAndView("index");
    ModelAndView finalPaginaInicial = new ModelAndView("PaginaInicial");
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        HttpSession session = request.getSession();
        
        Boolean isLogged = (boolean) session.getAttribute("userLogin");
        if(isLogged){
            response.sendRedirect(request.getContextPath()+"/paginainicial.htm");
            return finalPaginaInicial;
        } else{
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
                            session.setAttribute("userLogin", isLogged);
                            session.setAttribute("leitor", user_txt);
                    
                            response.sendRedirect(request.getContextPath()+"/paginainicial.htm");
                            return finalPaginaInicial;
                        } else{
                            System.out.println("Não tem permissões para entrar na Página.");
                            response.sendRedirect(request.getContextPath()+"/paginalogin.htm");
                            return finalLogin;
                        }
                
                    } else{
                        System.out.println("Credenciais Erradas.");
                        response.sendRedirect(request.getContextPath()+"/paginalogin.htm");
                        return finalLogin;
                    }
                }catch(NullPointerException npe){
                    npe.printStackTrace();
                    System.out.println("Conta não existe.");
                    response.sendRedirect(request.getContextPath()+"/paginalogin.htm");
                    return finalLogin;
                }
            }
        }
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        return finalLogin;
    } 
}