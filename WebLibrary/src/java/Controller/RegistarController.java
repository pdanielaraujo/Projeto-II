/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author Pedro
 */
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
        }
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
}
