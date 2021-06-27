/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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
@RequestMapping(value = "/index.htm")
public class IndexController extends AbstractController {
    
    public IndexController() {
    }
    
    ModelAndView finalLogin = new ModelAndView("Login");
    ModelAndView finalRegister = new ModelAndView("Registar");
    ModelAndView finalIndex = new ModelAndView("index");
    ModelAndView finalPaginaInicial = new ModelAndView("PaginaInicial");
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        HttpSession session = request.getSession();
        
        if(request.getParameter("submitGoToLogin") != null){
            System.out.println("aaaa");
            boolean isLogged = false;
            session.setAttribute("userLogin", isLogged);
            response.sendRedirect(request.getContextPath()+"/paginalogin.htm");
            return finalLogin;
        }
        
        if(request.getParameter("submitGoToRegister") != null){
            System.out.println("vvvv");
            boolean isLogged = false;
            session.setAttribute("userLogin", isLogged);
            response.sendRedirect(request.getContextPath()+"/paginaregistar.htm");
            return finalRegister;
        }
        
        try{
            boolean isLogged = (boolean) session.getAttribute("userLogin");
            if(isLogged){
                response.sendRedirect(request.getContextPath()+"/paginainicial.htm");
                return finalPaginaInicial;
            } else{
                return finalIndex;
            }
        } catch(NullPointerException npe){
            return finalIndex;
        }
    }
}